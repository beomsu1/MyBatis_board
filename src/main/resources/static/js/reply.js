/* 댓글 비동기 통신 */

const url = `http://localhost:8080/api/replies/`

// list
// replyLast - false -> 댓글 마지막 페이지로 이동
const replyGetList = async(replyLast = false , page= 1) => {
    const res = await axios.get(`${url}${tno}/list/?page=${page}&replyLast=${replyLast}`)
    return res.data
}

// regist
const replyPostRegist = async(data) => {
    const res = await axios.post(`${url}${tno}/regist`,data)
    return res.data
}

// read

// modify

// delete
