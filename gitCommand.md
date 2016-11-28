初始化：git init
初始化后同步远程仓库：git remote add origin http://***.git
克隆远程分支：git clone -b <branchName> http://****.git
获取远程分支到本地，并创建对应分支：git fetch orgin <branchName>:<localBranchName>

同步代码： 
添加：git add .
查看：git status
提交：git commit -m "commit messag"
查看提交记录 git log
推送到远程分支：git push origin <branchName>
从远程分支拉取：git pull origin <branchName>


LF/CRLF编码问题，提交检出均不转换：git config --global core.autocrlf false