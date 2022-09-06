package DataPackage_Careernet_Specific

data class Content(
    val GenCD: List<GenCD>,
    val SchClass: List<SchClas>,
    val career_act: List<CareerAct>,
    val chartData: List<ChartData>,
    val department: String,
    val employment: String,
    val enter_field: List<EnterField>,
    val interest: String,
    val job: String,
    val lstHighAptd: List<LstHighAptd>,
    val lstMiddleAptd: List<LstMiddleAptd>,
    val lstVals: List<LstVal>,
    val main_subject: List<MainSubject>,
    val major: String,
    val `property`: String,
    val qualifications: String,
    val relate_subject: List<RelateSubject>,
    val salary: String,
    val summary: String,
    val university: List<University>
)