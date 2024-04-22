import axios from 'axios'
import { parseCookies } from 'nookies';

// export default function AxiosConfig(){
//     return {
//         headers: {
//             "Cache-Control": "no-cache",
//             "Content-Type": "application/json",
//             Authorization: `Bearer blah ~`,
//             "Access-Control-Allow-Origin": "*",
//         }
//     }
// }


const instance = axios.create({ baseURL: process.env.NEXT_PUBLIC_API_URL})

instance.interceptors.request.use(
    (request) => {
        const accessToken = parseCookies().accessToken;
        console.log('AXIOS 인터셉터 에서 쿠키에서 토큰 추출함')
        request.headers['Content-Type'] = "application/json"
        request.headers['Authorization'] = `Bearer ${accessToken}`
        return request
    },
    (error) => {
        console.log('AXIOS 인터셉터 에서 발생한 에러 : '+error)
        return Promise.reject(error)
    }
)

instance.interceptors.response.use(
    (response) => {
        if(response.status === 404){
            console.log('AXIOS 인터셉터 에서 발생한 에러로 토큰이 없어서 404 페이지로 넘어감')
        }
        return response
    }
)




export default instance
