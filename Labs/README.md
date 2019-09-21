# SOEN-387
Assignments and Projects for SOEN 387

# Setting up Server
- Choose Project: File > New Project > Java with Ant > Java Web > Web Application
- Name and Location: Modify "Project Name" and "Project Location" if desired.
- Server and Settings: Server > Apache Tomcat or TomEE
  - Pick Server Location (Catalina Home) where "apache-tomcat-9.0.26" folder is located
- Framework: Leave as is.

Before "Running" the Web App, head to "apache-tomcat-9.0.26" folder.
Run `chmod a+x *.sh`: to ensure the scripts can be executed.
You can run it on Apache Netbeans without having to run `sh startup.sh`
Reference: https://stackoverflow.com/questions/23081395/start-of-tomcat-failed#23081506
