@echo OFF
@echo Building at %time%
IF "%1"=="local" GOTO local
@echo Pushing to remote
rem copy activities.php C:\wamp64\www\tracker\ /Y
rem xcopy api C:\wamp64\www\tracker\api\ /Y /E
GOTO complete

:local
@echo Pushing to local
copy activities.php C:\wamp64\www\tracker\ /Y
copy recent-activities.php C:\wamp64\www\tracker\ /Y
xcopy api C:\wamp64\www\tracker\api\ /Y /E

:complete
@echo -----
@echo Build complete