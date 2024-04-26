import { createAsyncThunk } from "@reduxjs/toolkit";
import { deleteArticleAPI, findAllArticlesAPI, findArticleByIdAPI, saveArticleAPI } from "./article-api";
import IArticle from "../model/article";


export const findAllArticles: any = createAsyncThunk(
    'articles/findAllArticles',
    async (page: number)=>( await  findAllArticlesAPI(page))
)
export const findArticleById: any = createAsyncThunk(
    'articles/findArticleById',
    async (articleId: number)=>( await  findArticleByIdAPI(articleId))
)
export const saveArticle: any = createAsyncThunk(
    'articles/saveArticle',
    async (article: IArticle)=>{ await  saveArticleAPI(article)}
)
export const deleteArticle: any = createAsyncThunk(
    'articles/deleteArticle',
    async (id: number)=>{ await  deleteArticleAPI(id)})
