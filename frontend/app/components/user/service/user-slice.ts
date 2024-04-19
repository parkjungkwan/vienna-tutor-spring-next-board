import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';
import { createSlice } from "@reduxjs/toolkit";
import { IUser } from '../model/user';

import { existsUsername, findAllUsers, findUserById, login } from './user-service';

const userThunks = [findAllUsers,findUserById]

const status = {
    pending: 'pending',
    fulfilled: 'fulfilled',
    rejected: 'rejected'
}

interface IAuth{
    message?: string,
    token?: string
}

interface UserState  {
    array? : Array<IUser>,
    json?:IUser,
    auth?: IAuth,
    existsUsername?: boolean

}

export const initialState:UserState = {
    json: {} as IUser,
    array : [],
    auth: {} as IAuth,
    existsUsername: false
}

export const userSlice = createSlice({
    name: "users",
    initialState,
    reducers: {},
    extraReducers: builder => {
        const {pending, rejected} = status;

        builder
        .addCase(findAllUsers.fulfilled,  (state: any, {payload}: any) => {state.array=payload})
        .addCase(findUserById.fulfilled,  (state: any, {payload}: any) => {state.json=payload})
        .addCase(login.fulfilled,  (state: any, {payload}: any) => {state.auth=payload})
        .addCase(existsUsername.fulfilled,  (state: any, {payload}: any) => {state.existsUsername=payload})
  
    }
})
export const getAllUsers = (state: any) =>(state.user.array)
export const getUserById = (state: any) =>(state.user.json)
export const getAuth = (state: any) =>(state.user.auth)
export const getExistsUsername = (state: any) =>(state.user.existsUsername)

export const {} = userSlice.actions

export default userSlice.reducer;