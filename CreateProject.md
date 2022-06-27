# プロジェクトの作成
.NET C#, Java Script, Python, Type Script 用
```
$func_ver = 3
$func_worker = @('dotnet', 'dotnet-isolated', 'javascript', 'powershell', 'python', 'typescript')

funcvm use $func_ver
foreach ($worker in $func_worker) {
    $project_dir = "functions-v" +$func_ver+ "-" +$worker

    func init $project_dir --worker-runtime $worker --docker
    If( $worker -eq 'dotnet' -or $worker -eq 'dotnet-isolated' ){
        If( $func_ver -eq 3 ){
            Copy-Item 'global_3.json' "$project_dir\\global.json"
        }ElseIf( $func_ver -eq 4 ){
            Copy-Item 'global_3.json' "$project_dir\\global.json"
        }
    }
    cd $project_dir

    funcvm use $func_ver --local
    func new --name HttpTrigger1 --template "HTTP trigger" --authlevel function

    cd ../
}
```

## なぜ global.json が必要になるのか
dotnet コマンドはインストールされているもののうち、最新が使用される。
.NET 6 がインストールされている環境では、Funciont v3 (.NET Core 3.1) の関数が作成できないための措置 

```
dotnet --list-sdks
dotnet new global.json
```


# プロジェクトの作成 (Java)
```
mvn archetype:generate "-DarchetypeGroupId=com.microsoft.azure" "-DarchetypeArtifactId=azure-functions-archetype" "-DjavaVersion=8" `
  "-DinteractiveMode=false" `
  "-DgroupId=com.fabrikam" `
  "-DartifactId=functions-v3-java8" `
  "-Dversion=1.0-SNAPSHOT" `
  "-Dpackage=com.fabrikam"

mvn archetype:generate "-DarchetypeGroupId=com.microsoft.azure" "-DarchetypeArtifactId=azure-functions-archetype" "-DjavaVersion=11" `
  "-DinteractiveMode=false" `
  "-DgroupId=com.fabrikam" `
  "-DartifactId=functions-v3-java11" `
  "-Dversion=1.0-SNAPSHOT" `
  "-Dpackage=com.fabrikam"
```