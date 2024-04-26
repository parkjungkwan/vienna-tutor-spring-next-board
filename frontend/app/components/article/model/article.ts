export default interface IArticle{
    articleId? : number
    title? : string
    content? : string
    writerId? : number
    boardId? : number
    regDate? : string
    modDate? : string
    json?: {}
    array?: IArticle[]
}
