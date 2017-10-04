package com.ikechukwuakalu.daggermvp.userdetails

import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.BaseScheduler
import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.ImmediateScheduler
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserDetailsPresenterTest {

    val userLogin = "someLogin"

    val user = User(23, userLogin, "http://avatar2.com", "http://url-user2.com", "Jane Doe")

    @Mock
    lateinit var view: UserDetailsContract.View

    @Mock
    lateinit var usersRepo : UsersRepository

    lateinit var presenter : UserDetailsPresenter

    lateinit var scheduler : BaseScheduler

    @Before
    fun setUpPresenter() {

        MockitoAnnotations.initMocks(this)

        scheduler = ImmediateScheduler()

        presenter = UserDetailsPresenter(usersRepo, scheduler, userLogin)
        presenter.attach(view)

    }

    @Test
    fun loadUserDetailsIntoView() {
        // Given
        Mockito.`when`(usersRepo.getUser(userLogin)).thenReturn(Observable.just(user))
        // When
        presenter.fetchUserDetails()
        // Then
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showUserDetails(user)
    }

}