function makePage(page,size,total){

let pagingResult = ""

// 페이지 첫번쨰 번호
// 현재 페이지 / 10 한후 올리고
// 10 곱하고 -9처리

const startNum = (Math.ceil(page/10) *10) -9;

// 시작번호가 1이면 이전버튼 노출 X

startNum == 1 ? "" : pagingResult += `<a href="${startNum - 1}" class="btn btn-primary"><</a>`

// 페이징 번호 변수
let temp = startNum

// 페이징 버튼 동적 생성 / while(true) 무한 반복문사용
while(true){

    // 페이징 번호 * 10 이 total보다 크면 break
    if(temp * 10 > total){
        break
    }

    // temp == page -> 활성화
    temp == page ? pagingResult += `<a href="${temp}" class="btn btn-primary active">${temp}</a>` : pagingResult += `<a href="${temp}" class="btn btn-primary">${temp}</a>`

    temp ++
}

// total이 size*10을 나눈 나머지가 1이면 다음 페이지 노출

total %(size*10) == 1? pagingResult += `<a href="${temp}" class="btn btn-primary">></a>` : ""

return pagingResult;

}