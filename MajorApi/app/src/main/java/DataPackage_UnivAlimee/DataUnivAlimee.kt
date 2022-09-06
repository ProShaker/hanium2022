package DataPackage_UnivAlimee

data class DataUnivAlimee(
    val currentCount: Int, // 현재 페이지 데이터 수
    val data: List<Data>, // 대학 정보
    val matchCount: Int, //전체 데이터 수
    val page: Int,  // 현재 페이지 번호
    val perPage: Int, // 한 페이지당 정보 수
    val totalCount: Int //전체 데이터 수
)