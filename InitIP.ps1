function Get-ScriptDirectory {
    if ($psise) {
        Split-Path $psise.CurrentFile.FullPath
    }
    else {
        $global:PSScriptRoot
    }
}

$ipaddress= (Get-NetIPConfiguration | where { $_.ipv4defaultgateway -ne $null }).IPv4Address.IPAddress
$ScriptPath = Get-ScriptDirectory
$Files = Get-ChildItem -Path $ScriptPath -Filter "*application.properties" -Recurse
foreach($File in $Files){
    ((Get-Content -path $File.FullName -Raw) -replace '<IP>',$ipaddress) | Set-Content -Path $File.FullName
}

 