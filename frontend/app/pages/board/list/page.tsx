"use client"

import CardButton from "@/app/atoms/button/CardButton";
import { IBoard } from "@/app/components/board/model/board";
import { findAllBoards } from "@/app/components/board/service/board-service";
import { getAllBoards } from "@/app/components/board/service/board-slice";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux"


export default function BoardCards(){



    const dispatch = useDispatch()
    const allBoards = useSelector(getAllBoards);
    useEffect(()=>{
        dispatch(findAllBoards(1))
    }, [])
    

    return (<>
    
   <h1>게시판 목록 들어옴</h1>

   {allBoards?.map((board: IBoard)=>(
   <li key={board.boardId}>
   <CardButton boardId={board.boardId}  title={board.title} description={board.description}/>
   </li>
   ))}

    </>)
}