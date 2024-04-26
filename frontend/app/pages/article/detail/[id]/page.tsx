'use client'

import IArticle from "@/app/components/article/model/article";
import {findArticleById } from "@/app/components/article/service/article-service";

import { PG } from "@/app/components/common/enums/PG";

import { Button, Input } from "@mui/material";
import { useRouter } from "next/navigation";

import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";


export default function ArticleDetailPage({params}:any){
    const dispatch = useDispatch();
  
    const router = useRouter();

    useEffect(() => {
        dispatch(findArticleById(params.id))
    },[])



    return (<div className="text-center">
            <p className="text-xl">Article Detail</p><br />
            <p className="text-base">ID : {params.id}</p>
            <span className="text-base">Title : </span><Input className="text-base" placeholder={''} onChange={()=>console.log('')} /><br />
            <span className="text-base">Content : </span><Input className="text-base" placeholder={''} onChange={()=>console.log('')} /><br />
            <p className="text-base">Register Date :</p>
            <p className="text-base">Modified Date : </p>
            <Button onClick={()=>console.log('')}>Update</Button>
            <Button onClick={()=>console.log('')}>Delete</Button>
        </div>)
}