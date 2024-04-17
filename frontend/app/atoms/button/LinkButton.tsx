import { PG } from "@/app/components/common/enums/PG";
import Link from "next/link"

interface ILinkButton{
    title: string,
    path: string
}

export default function LinkButton ({title, path}:ILinkButton) {
    return ( <Link href={`${path}`}
                    className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                     md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                      dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent
                       dark:border-gray-700" aria-current="page">
                        {title}
            </Link>)
    }


  export  const linkButtonTitles = [
        {title:'회원가입', path:`${PG.USER}/join`},
        {title:'로그인', path:'/'}, 
        {title:'카운터', path:`${PG.DEMO}/counter`},
        {title:'게시판목록', path:`${PG.BOARD}/list`},
        {title:'게시글목록', path:`${PG.ARTICLE}/list`}, 
        {title:'사용자목록', path:`${PG.BOARD}/list`}
      ];


    export  const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];