import { createAsyncThunk } from "@reduxjs/toolkit";

import { existsUsernameAPI, findAllUsersAPI, findUserByIdAPI, loginAPI, logoutAPI } from "./user-api";
import { IUser } from "../model/user";

export const findAllUsers: any = createAsyncThunk(
    'users/findAllUsers',
    async (page: number)=> await  findAllUsersAPI(page)
)
export const findUserById: any = createAsyncThunk(
    'users/findUserById',
    async (id: number)=> await  findUserByIdAPI(id)
)

export const login: any = createAsyncThunk(
    'users/login',
    async (user: IUser)=> await  loginAPI(user)
)

export const existsUsername: any = createAsyncThunk(
    'users/existsUsername',
    async (username: string)=> await  existsUsernameAPI(username)
)

export const logout: any = createAsyncThunk(
    'users/logout',
    async () => await logoutAPI()
)