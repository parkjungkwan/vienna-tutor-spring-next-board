import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';
import { createSlice } from "@reduxjs/toolkit";
import { IBoard } from '../model/board';
import { findAllBoards, findBoardById } from './board-service';


interface BoardState{
    json: IBoard
    array: Array<IBoard>
}

export const initialState:BoardState = {
    json: {} as IBoard,
    array: []


}


const boardThunks = [findAllBoards,findBoardById]

const status = {
    pending: 'pending',
    fulfilled: 'fulfilled',
    rejected: 'rejected'
}


export const boardSlice = createSlice({
    name: "boards",
    initialState,
    reducers: {},
    extraReducers: builder => {
        const {pending, rejected} = status;

        builder
        .addCase(findAllBoards.fulfilled, (state: any, {payload}: any) =>{state.array=payload})
        .addCase(findBoardById.fulfilled, (state: any, {payload}: any) =>{state.json=payload})

  
    }
})
export const getAllBoards = (state: any) =>(state.board.array);
export const getSingleBoard = (state: any) =>(state.board.json);

export const {} = boardSlice.actions

export default boardSlice.reducer;