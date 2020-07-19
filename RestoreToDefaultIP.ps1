function Get-ScriptDirectory {
    if ($psise) {
        Split-Path $psise.CurrentFile.FullPath
    }
    else {
        $global:PSScriptRoot
    }
}
$ScriptPath = Get-ScriptDirectory
$ipaddress=([System.Net.DNS]::GetHostAddresses($env:COMPUTERNAME)|Where-Object {$_.AddressFamily -eq "InterNetwork"}   |  select-object IPAddressToString)[0].IPAddressToString
$Files = Get-ChildItem -Path $ScriptPath -Filter "*application.properties" -Recurse
foreach($File in $Files){
    ((Get-Content -path $File.FullName -Raw) -replace $ipaddress,'<IP>') | Set-Content -Path $File.FullName
}

 