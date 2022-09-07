package Var_Set

object UrlSet {

    // 커리어넷 API URL(베이스 url)
    val BASE_URL_Careernet = "https://www.career.go.kr/cnet/openapi/"

    // 커리어넷 API URL(전체 리스트 엔드포인트)
    const val Endpoint_URL_Careernet = "getOpenApi?apiKey=b80cf8532c4e33c60ff8cf4c67b7962c&svcType=api&svcCode=MAJOR&contentType=json&gubun=univ_list"

    // 커리어넷 API URL(일반대학 리스트 엔드포인트)
    const val Endpoint_Careernet_List_Univ = "getOpenApi?apiKey=b80cf8532c4e33c60ff8cf4c67b7962c&svcType=api&svcCode=MAJOR&contentType=json&gubun=univ_list&univSe=univ&thisPage=1&perPage=114"

    // 커리어넷 API URL(전문대학 리스트 엔드포인트)
    const val Endpoint_Careernet_List_College = "getOpenApi?apiKey=b80cf8532c4e33c60ff8cf4c67b7962c&svcType=api&svcCode=MAJOR&contentType=json&gubun=univ_list&univSe=college&thisPage=1&perPage=114"

    // 커리어넷 API URL(일반대학 상세 엔드포인트)
    const val Endpoint_Careernet_Specific_Univ = "getOpenApi?apiKey=b80cf8532c4e33c60ff8cf4c67b7962c&svcType=api&svcCode=MAJOR_VIEW&contentType=json&gubun=univ_list&univSe=univ&thisPage=1&perPage=114"

    // 커리어넷 API URL(전문대학 상세 엔드포인트)
    const val Endpoint_Careernet_Specific_College = "getOpenApi?apiKey=b80cf8532c4e33c60ff8cf4c67b7962c&svcType=api&svcCode=MAJOR_VIEW&contentType=json&gubun=univ_list&univSe=college&thisPage=1&perPage=114"

}

// map자료형을 이용한 엔드포인트의 subject 요청변수 번호
val MajorSubject = mapOf<String, Int>(
    "인문계열" to 100391,
    "사회계열" to 100392,
    "교육계열" to 100393,
    "공학계열" to 100394,
    "자연계열" to 100395,
    "의약계열" to 100396,
    "예체능계열" to 100397
)
// 전공유형을 저장하는 변수
var majorTypeName: Int? = 0

// 전공번호를 저장하는 변수 - API URL(상세)
var majorSeqNumb : Int = -1

// 학과명, 학과 개요, 적성을 저장하는 함수(프래그먼트에 사용)
var majorName : String = " "
var majorSummary : String = " "
var majorInterest : String = " "

// 대학 유형 값을 저장하는 bool 변수 - API 호출
var UnivType : Boolean = true

// 프래그먼트 작동을 알리는 변수
var fragmentActivate  = false

// 선택된 전공의 인덱스를 저장하는 변수
var selectedMajor = -1

// API 에서 불러온 데이터를 저장하는 리스트
var majorNameList : MutableList<String> = mutableListOf()
var majorNumbList : MutableList<String> = mutableListOf()



