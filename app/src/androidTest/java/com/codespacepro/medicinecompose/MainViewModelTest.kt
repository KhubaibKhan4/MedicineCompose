package com.codespacepro.medicinecompose

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codespacepro.medicinecompose.model.Medicines
import com.codespacepro.medicinecompose.repository.Repository
import com.codespacepro.medicinecompose.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: MainViewModel




    @Before
    fun setup() {
        repository = Repository()
        viewModel = MainViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetMedicines() = runBlockingTest {
        val mockResponse: Response<Medicines> = Response.success(null) // You can customize the mock response if needed

        `when`(repository.getMedicines()).thenReturn(mockResponse)

        viewModel.getMedicines()

        val responseLiveData = viewModel.myResponse
        val observedResponse = responseLiveData.value

        assertEquals(mockResponse, observedResponse)
    }

}
