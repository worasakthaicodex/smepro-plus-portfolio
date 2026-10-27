Option Explicit
Dim objWord, strdocPath, objDoc

strdocPath = "DOC_FILE"
   
 
Set objWord = CreateObject("Word.Application")
Set objDoc = objWord.documents.open(strdocPath)
objWord.Visible = False
objDoc.saveas "PDF_FILE", 17
objDoc.Close
objWord.Quit  
       