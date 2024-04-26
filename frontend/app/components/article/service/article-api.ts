import instance from '@/app/components/common/configs/axios-config'
import IArticle from '../model/article'

export const findAllArticlesAPI = async (page: number) =>{
    try{
        const response = await instance().get('/articles/list',{
            params: {page, limit: 10}
        })
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
    
}

export const findArticleByIdAPI = async (id: number) =>{
    try{
        const response = await await instance().get(`/boards/detail`,{params: {id}})
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
    
}

export const saveArticleAPI = async (article: IArticle) =>{
    console.log("게시글 API 확인 ", JSON.stringify(article) )
    try{
        const response = await await instance().post(`/articles/save`,article)
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
    
}

export const deleteArticleAPI = async (id: number) =>{
    try{
        const response = await await instance().get(`/boards/delete`,{params: {id}})
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
    
}
