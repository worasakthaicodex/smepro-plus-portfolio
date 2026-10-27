Option Explicit
Dim objPowerpoint, strpttPath, objPtt

strpttPath = "PTT_FILE"
   
Set objPowerpoint = CreateObject("Powerpoint.Application")
Set objPtt = objPowerpoint.Presentations.open(strpttPath)
objptt.SaveAs "PDF_FILE", 32
objPtt.Close
objPowerpoint.Quit