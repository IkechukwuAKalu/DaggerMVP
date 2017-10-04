package com.ikechukwuakalu.daggermvp.users

import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.models.Api
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.ImmediateScheduler
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UsersPresenterTest{

    private val users = arrayOf(
            User(23, "aikay", "http://aikay-avatar.com", "Leonard", "http://aikay.com"),
            User(63, "chioma", "http://chioma-avatar.com", "Chioma", "http://chioma.com")
            )

    private lateinit var api : Api

    @Mock
    lateinit var userRepo : UsersRepository

    @Mock
    lateinit var view: UsersContract.View

    lateinit var presenter: UsersPresenter

    lateinit var scheduler : ImmediateScheduler

    @Before
    fun setUpPresenter(){
        MockitoAnnotations.initMocks(this)

        scheduler = ImmediateScheduler()
        presenter = UsersPresenter(userRepo, scheduler)

        api = Api(users)

        presenter.attach(view)
    }

    @Test
    fun loadUsersFromRepositoryIntoView() {
        // Given a city and users
        val city = "London"
        Mockito.`when`(userRepo.getUsers(city)).thenReturn(Observable.just(api))
        // When users are loaded
        presenter.loadUsers(city)
        Mockito.verify(view).showLoading()
        // Then loader is hidden
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showUsers(api.items.toList())
    }

    @Test
    fun clickOnUser_ShowUserDetails() {
        // Given a User login
        val login = "IkechukwuAKalu"
        // When User is clicked
        presenter.openUserDetails(login)
        // Then user details is shown
        Mockito.verify(view).showUserDetails(login)
    }

}