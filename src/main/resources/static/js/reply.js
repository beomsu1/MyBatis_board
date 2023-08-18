/* 댓글 비동기 통신 */

const url = `http://localhost:8080/api/replies/`

// list
// replyLast - false -> 댓글 마지막 페이지로 이동
const replyGetList = async(replyLast = false, page = 1) => {
    const res = await axios.get(`${url}${tno}/list?page=${page}&replyLast=${replyLast}`)
    return res.data
  }



// regist
const replyPostRegist = async(replyData) => {
    const res = await axios.post(`${url}${tno}/regist`,replyData)
    return res.data
}

// read
const replyGetRead = async(rno) => {
    const res = await axios.get(`${url}/read/${rno}`)
    return res.data
}

// modify
const replyPutModify = async(replyData) => {
    const res = await axios.put(`${url}modify/${replyData.rno}`, replyData)
    return res.data
}


// delete
const replyDelete = async(rno) => {
    const res = await axios.delete(`${url}delete/${rno}`)
    return res.data
}

const getListDefault = (replyLast, page) => {
    replyGetList( replyLast, page).then(arr => {
      let replyStr = ""
      let replyPagingStr = ""
      //console.log(arr)
      for(let i = 0; i < arr.list.length; i++){
        const {reply, replyer, replyDate, step, gno, rno} = arr.list[i]
        replyStr += `
          <div class="d-flex align-items-center py-3 border-top${step === 0 ? "" : " ps-3"}">
            <div class="w-100">
              <div class="d-flex w-100 justify-content-between">
                <h6 class="mb-0">${reply}</h6>
              </div>
              <span>${replyer}</span>
              <small class="mx-3">${replyDate}</small>
              <button class="btn btn-outline-secondary" data-reply="reply" data-gno="${gno}">Reply</button>
              <button class="btn btn-outline-primary" data-reply="modify" data-rno="${rno}">Modify</button>
            </div>
          </div>
        `
      }
  
      const {page, size, startNum, endNum, prevBtn, nextBtn, replyLast, total} = arr
      console.log(arr)
  
      prevBtn === true ? replyPagingStr += `<li><button data-page="${startNum - 1} class="btn btn-primary"><</button></li>` : ""
  
      for(let i = startNum; i <= endNum; i++){
        replyPagingStr += `
          <li${page === i ? " class='active'" : ''}>
            <button data-page="${i}" class="btn btn-primary">${i}</button>
          </li>
        `
      }
  
      nextBtn === true ? replyPagingStr += `<li><button data-page="${endNum + 1} class="btn btn-primary">></button></li>` : ""
  
      //console.log(replyLast = Math.ceil((page * size) / total) === 1 ? true : false)
      //console.log(replyStr)
      //console.log(replyPagingStr)
      replyWrap.innerHTML = replyStr
      replyPaging.innerHTML = replyPagingStr
    })
  }