package com.example.ass_kotlin_luatdcph35698.Repository

import com.example.ass_kotlin_luatdcph35698.DTO.AccountRespone
import com.example.ass_kotlin_luatdcph35698.Model.AccountCT
import com.example.ass_kotlin_luatdcph35698.Service.AccountService
import retrofit2.Response

class AccountRepository(private val accountService: AccountService) {

    suspend fun updateAccount(id: String, account: AccountCT): Response<AccountRespone> {
        return accountService.updateAccount(id, account)
    }
}