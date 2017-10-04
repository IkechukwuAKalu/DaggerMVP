package com.ikechukwuakalu.daggermvp.userdetails

import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.BaseScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ActivityScoped
class UserDetailsPresenter @Inject constructor(private var usersRepository: UsersRepository, private var rxScheduler: BaseScheduler,
                                               @UserLogin private var login: String) : UserDetailsContract.Presenter {

    var detailsView: UserDetailsContract.View? = null

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attach(view: UserDetailsContract.View) {
        detailsView = view
    }

    override fun detach() {
        detailsView = null
        compositeDisposable.clear()
    }

    override fun fetchUserDetails() {
        detailsView?.showLoading()

        val disposable : Disposable = usersRepository.getUser(login)
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.ui())
                .subscribe({
                    // onNext()
                    detailsView?.hideLoading()
                    if (it == null) {
                        detailsView?.showErrorLoadingUser("User not found")
                    } else {
                        detailsView?.setTitle(it.login)
                        detailsView?.showUserDetails(it)
                    }
                }, {
                    // onError()
                    detailsView?.hideLoading()
                    detailsView?.showErrorLoadingUser(it.message.toString())
                })
        compositeDisposable.clear()
        compositeDisposable.add(disposable)
    }
}