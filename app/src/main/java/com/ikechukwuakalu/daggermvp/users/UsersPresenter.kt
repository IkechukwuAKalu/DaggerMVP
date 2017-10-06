package com.ikechukwuakalu.daggermvp.users

import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.utils.espresso.EspressoIdlingResource
import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.BaseScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ActivityScoped
class UsersPresenter @Inject constructor (private var usersRepo: UsersRepository,
                                          private var rxScheduler: BaseScheduler) : UsersContract.Presenter{

    private var view : UsersContract.View? = null

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attach(view: UsersContract.View) {
        this@UsersPresenter.view = view
    }

    override fun detach() {
        view = null
        compositeDisposable.clear()
    }

    override fun loadUsers(city: String?) {
        val location = city?.capitalize() ?: "Lagos"
        view?.hideUsers()
        view?.showLoading()

        var newUsers = emptyList<User>()
        if (location == "Lagos") {
            newUsers += listOf(sponsoredUser())
        }

        EspressoIdlingResource.increment()
        val disposable : Disposable = usersRepo.getUsers(location)
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.ui())
                .doFinally {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow)
                        EspressoIdlingResource.decrement()
                }
                .subscribe({
                    // onNext()
                    val users : List<User> = it.items.toList()
                    newUsers += users
                    view?.hideLoading()
                    view?.showUsers(newUsers)
                    view?.setTitle("Github Devs in $location")
                }, {
                    // onError()
                    view?.hideLoading()
                    view?.showErrorLoadingUsers(it.message.toString())
                })
        compositeDisposable.clear()
        compositeDisposable.add(disposable)
    }

    override fun openUserDetails(login: String) {
        view?.showUserDetails(login)
    }

    /**
     * This is me :-)
     */
    private fun sponsoredUser() : User {
        return User(id = 25153373, login = "IkechukwuAKalu", avatar_url = "https://avatars2.githubusercontent.com/u/25153373?v=4",
                name = "Ikechukwu A. Kalu", url = "https://api.github.com/users/IkechukwuAKalu")
    }
}