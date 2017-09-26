package com.ikechukwuakalu.daggermvp.users

import com.ikechukwuakalu.daggermvp.capture
import com.ikechukwuakalu.daggermvp.data.UsersDataSource
import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.eq
import org.junit.Before
import org.junit.Test
import org.mockito.*

class UsersPresenterTest{

    val users = listOf(User(23, "aikay", "http://url.com", "Leonard", "http://userUrl"))

    @Mock
    lateinit var userRepo : UsersRepository

    @Mock
    lateinit var view: UsersContract.View

    lateinit var presenter: UsersPresenter

    @Captor
    lateinit var loadUsersCallbackCaptor: ArgumentCaptor<UsersDataSource.LoadUsersCallback>

    @Before
    fun initPresenter(){
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(userRepo)
    }

    @Test
    fun checkUsersShown(){
        val city = "Lagos"
        presenter.loadUsers(city)
        Mockito.verify(userRepo).getUsers(eq(city), capture((loadUsersCallbackCaptor)))
        loadUsersCallbackCaptor.value.onSuccess(users)
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showUsers(users)
    }

}