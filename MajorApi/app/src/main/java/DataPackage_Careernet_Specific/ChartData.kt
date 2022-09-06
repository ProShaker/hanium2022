package DataPackage_Careernet_Specific

data class ChartData(
    val after_graduation: List<AfterGraduation>,
    val applicant: List<Applicant>,
    val avg_salary: List<AvgSalary>,
    val employment_rate: List<EmploymentRate>,
    val `field`: List<Field>,
    val gender: List<Gender>,
    val satisfaction: List<Satisfaction>
)