package com.afsal.dev.filimapp.repositoryimport com.afsal.dev.filimapp.network.Resourceimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.withContextimport retrofit2.HttpExceptionabstract class BaseRepository {    suspend fun <T> safApiCall(apiCall:suspend ()->T):Resource<T>{        return withContext(Dispatchers.IO){            try {                Resource.Success(apiCall.invoke())            }catch (throwable:Throwable){                when(throwable){                    is HttpException ->{                        Resource.Failure(false,throwable.code(),throwable.response()?.errorBody())                    }//                    is  Exception ->{//                        Resource.Failure(true,throwable.code(),null)////                    }                    else ->{                        Resource.Failure(true,null,null)                    }                }            }        }    }}