/* 댓글 비동기 통신 */

const url = `http://localhost:8080/api/replies/`

// list


// regist
const replyRegist = async(data) => {
    const res = await axios.post(`${url}${tno}/regist`,data)
    return res.data
}

// read

// modify

// delete