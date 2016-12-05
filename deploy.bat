@echo OFF
@echo Building at %time%
set OLDDIR=%CD%
IF "%1"=="local" GOTO local
IF "%1"=="prod" GOTO prod
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
GOTO complete

:prod
rmdir dist /s /q
mkdir dist
copy activities.php dist /Y
copy recent-activities.php dist /Y
copy recorded-activities.php dist /Y
xcopy api dist /Y /E
cd dist
for /f "Tokens=*" %f in ('cmd /c "echo %cd%& dir /l/b/ad/s"') do (for /f "Tokens=*" %g in ('dir /l/b/a-d "%f"') do (rename "%f"\"%g" "%g"))
:complete
@echo -----
@echo Build complete