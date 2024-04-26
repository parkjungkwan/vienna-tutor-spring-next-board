'use client'

import { useRouter } from "next/navigation"
import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import {Box, Button, Input} from '@mui/material';
import { useSelector, useDispatch } from 'react-redux'

import { IUser } from "@/app/components/user/model/user";
import { getUserById } from "@/app/components/user/service/user-slice";
import { findUserById } from "@/app/components/user/service/user-service";

export default function UserDetail({params}:any){
    const dispatch = useDispatch()
    const user:IUser = useSelector(getUserById) 
    useEffect(()=>{
        dispatch(findUserById(params.id))
    },[]) 

    return(<>
    {user.id}
    {user.username}
    {user.password}
    {user.job}

    <button>수정</button>
    <button>탈퇴</button>
    </>);
}