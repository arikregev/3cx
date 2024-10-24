# Yealink phones configuration fetcher
## Description:
This small springboot application will connect to designated 3CX PBX and will fetch all the SIP devices connected to it.</br>
The files will be saved on the running machine file-system.

## configuration - application.properties file:
logging.level.com.aregev.pbxconfig=INFO</br></br>
3cx.http.secure=true</br>
3cx.base.url=dummy.url.com</br>
3cx.port=443</br>
3cx.user.name=</br>
3cx.user.pass=</br>
</br></br>
save.config.folder=
