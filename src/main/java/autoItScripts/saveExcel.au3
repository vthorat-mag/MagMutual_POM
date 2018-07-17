
saveExcel()

Func saveExcel()

	Sleep(5000)
	send("{F6}") 
	send("{TAB}")
	send("{DOWN}")
	send("{DOWN}")
	send("{ENTER}")
	Sleep(3000)
	WinWaitActive("Save As")
	ControlSend("Save As","","Edit1","C:\TempsaveExcel\OnDemandInvoiceCredit.xlsx");
	Sleep(2000)
	ControlClick("Save As","&Save","Button1")
	Sleep(4000)
	if WinExists("Confirm Save As")then
		ControlClick("Confirm Save As","&Yes","Button1")
	else 
		send("{TAB}")
	EndIf
EndFunc   ;==>saveExcel