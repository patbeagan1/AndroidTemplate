//package com.example.domain
//
//import com.example.base.SingleUseCase
//import com.example.di.qualifiers.DateNow
//import com.example.di.qualifiers.ThreadIO
//import com.example.di.qualifiers.ThreadMain
//import io.reactivex.Scheduler
//import io.reactivex.Single
//import java.util.*
//import javax.inject.Inject
//
//class GetCatListDataUseCase @Inject constructor(
//    val getDateUseCase: GetDateUseCase,
//    val getCatUseCase: GetCatUseCase,
//    @ThreadMain val subscribeOn: Scheduler,
//    @ThreadIO val observeOn: Scheduler,
//    @DateNow val date: Date
//) : SingleUseCase<GetCatListDataUseCase.Param, GetCatListDataUseCase.Results>(
//    subscribeOn,
//    observeOn
//) {
//    override  fun mapEventToState(params: Param): Single<Results> {
//
//        val cats: Single<GetCatUseCase.Result> = getCatUseCase.partial(GetCatUseCase.Params())
//        val date: Single<GetDateUseCase.Results> = getDateUseCase.partial(GetDateUseCase.Param())
//        return date.zipWith(cats) { a, b -> Results(a, b) }
//    }
//
//    class Param
//    data class Results(
//        val dateResults: GetDateUseCase.Results,
//        val catResults: GetCatUseCase.Result
//    )
//}