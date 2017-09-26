package com.ikechukwuakalu.daggermvp.users

import com.ikechukwuakalu.daggermvp.capture
import com.ikechukwuakalu.daggermvp.data.UsersDataSource
import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.eq
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.*

class UsersPresenterTest{

    private val users = listOf(
            User(23, "aikay", "http://aikay-avatar.com", "Leonard", "http://aikay.com"),
            User(63, "chioma", "http://chioma-avatar.com", "Chioma", "http://chioma.com")
            )

    @Mock
    lateinit var userRepo : UsersRepository

    @Mock
    lateinit var view: UsersContract.View

    lateinit var presenter: UsersPresenter

    @Captor
    lateinit var loadUsersCallbackCaptor: ArgumentCaptor<UsersDataSource.LoadUsersCallback>

    @Before
    fun setUpPresenter(){
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(userRepo)
        presenter.attach(view)
    }

    @Test
    fun checkProgressHidden_UsersShown() {
        val city = "London"
        presenter.loadUsers(city)
        Mockito.verify(view).showLoading()
        Mockito.verify(userRepo).getUsers(eq(city), capture(loadUsersCallbackCaptor))
        loadUsersCallbackCaptor.value.onSuccess(users)
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showUsers(users)
    }

    @Test
    fun loadUsersFromRepositoryIntoView() {
        val city = "China"
        presenter.loadUsers(city)
        Mockito.verify(userRepo).getUsers(eq(city), capture(loadUsersCallbackCaptor))
        loadUsersCallbackCaptor.value.onSuccess(users)
        Mockito.verify(view).showUsers(users)
        val sizeArgumentCaptor = ArgumentCaptor.forClass(List::class.java)
        Mockito.verify(view).showUsers(capture(sizeArgumentCaptor) as List<User>)
        Assert.assertTrue(sizeArgumentCaptor.value.size == 2)
    }

}