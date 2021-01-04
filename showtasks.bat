call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startupbrowser
echo.
echo failed to run runcrud.bat
goto fail

:startupbrowser
start chrome http://localhost:8080/crud/v1/tasks

:fail
echo.
echo There were errors