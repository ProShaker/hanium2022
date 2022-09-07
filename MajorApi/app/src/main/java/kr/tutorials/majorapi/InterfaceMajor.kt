package kr.tutorials.majorapi

import DataPackage_Careernet_List.DatasetCareernetList
import DataPackage_Careernet_Specific.DatasetCareernetSpecific
import Var_Set.UrlSet.Endpoint_Careernet_List_College
import Var_Set.UrlSet.Endpoint_Careernet_List_Univ
import Var_Set.UrlSet.Endpoint_Careernet_Specific_College
import Var_Set.UrlSet.Endpoint_Careernet_Specific_Univ
import Var_Set.UrlSet.Endpoint_URL_Careernet
import Var_Set.majorSeqNumb
import Var_Set.majorTypeName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 커리어넷 리스트, 상세 API를 호출하는 인터페이스
interface APIMajor {

    //전체 리스트 불러오기
    @GET(Endpoint_URL_Careernet)
    fun getAllMajorData(): Call<DatasetCareernetList>

    //일반대학 리스트 불러오기
    @GET(Endpoint_Careernet_List_Univ)
    fun getUnivMajorList(@Query("subject") subject: Int? = majorTypeName): Call<DatasetCareernetList>

    //전문대학 리스트 불러오기
    @GET(Endpoint_Careernet_List_College)
    fun getCollegeMajorList(@Query("subject") subject: Int? = majorTypeName): Call<DatasetCareernetList>

    //일반대학 상세 불러오기
    @GET(Endpoint_Careernet_Specific_Univ)
    fun getUnivMajorSpecific(@Query("majorSeq") majorSeq: Int? = majorSeqNumb): Call<DatasetCareernetSpecific>

    //전문대학 상세 불러오기
    @GET(Endpoint_Careernet_Specific_College)
    fun getCollegeMajorSpecific(@Query("majorSeq") majorSeq: Int? = majorSeqNumb): Call<DatasetCareernetSpecific>

}

// fragmentPrinter 와 fragmentCounter 를 연결시키는 인터페이스
interface EventListener {
    fun onEvent(count: Int)
}