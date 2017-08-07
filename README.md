# CityGML-SimplifiedPipeline

1-Install Jenkins
2-Install Http Request Plugin
3-Install GitHub Authentication Plugin
4-Set up GitHub WebHooks
	GitHub -> Settings -> Webhooks & services
	Add service ->  Jenkins (GitHub plugin)
	http://3dcity.tecnalia.com:8080/github-webhook/
5-Install Git
6-Jenkins -> Global Tool Configuration -> Git -> Git installations -> Path to the git.exe [something like-> C:\Program Files\Git\cmd\git.exe]
7-Create job in Jenkins -> Freesyle
	-Configure Github repository
	-Check -> GitHub hook trigger for GITScm polling
8-Create jon in jenkins -> Pipeline
	-Check -> Build after other projects are built -> Select the previous job
	-Pipeline -> Copy the content in Jenkinsfile.txt
9-When pushing the CityGML file to the Github repository the pipeline is performed.


The XML and XSD validation services are included in the CityGMLValidationTools project developed in Eclipse IDE.