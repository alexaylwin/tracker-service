@echo OFF
@echo Building at %time%
IF "%1"=="local" GOTO local
@echo Pushing to remote
copy activities.php Z:\tracker-service\ /Y
copy recent-activities.php Z:\tracker-service\ /Y
copy recorded-activities.php Z:\tracker-service\ /Y
xcopy api Z:\tracker-service\api\ /Y /E
GOTO complete

:local
@echo Pushing to local
copy activities.php C:\wamp64\www\tracker\ /Y
copy recent-activities.php C:\wamp64\www\tracker\ /Y
copy recorded-activities.php C:\wamp64\www\tracker\ /Y
xcopy api C:\wamp64\www\tracker\api\ /Y /E

:complete
@echo -----
@echo Build complete