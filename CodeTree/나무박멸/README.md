# [보통] 나무박멸

[문제 링크](https://www.codetree.ai/frequent-problems/tree-kill-all/description) 

### 성능 요약

메모리: 11 MB, 시간: 147 ms

### 분류

구현(implementation), 시뮬레이션(simulation)

### 문제 설명
<div class="wmde-markdown wmde-markdown-color "><p>n * n 격자에 나무의 그루 수와 벽의 정보가 주어집니다.</p>
<p>나무의 성장과 번식력이 좋아서 제초제를 뿌려 나무의 성장을 억제하고자 합니다.
제초제의 경우 k의 범위만큼 대각선으로 퍼지며, 벽이 있는 경우 가로막혀서 전파되지 않습니다.
다음과 같이 초기 조건이 주어진다고 가정할 때, 1년동안 나무의 성장과 억제는 다음과 같이 이뤄집니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/6b47f725-f7b9-4b81-9b26-cb4d5fb348cf.png">
</p>
<ol>
<li>인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장합니다. 성장은 모든 나무에게 동시에 일어납니다.</li>
</ol>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/00714d98-f614-486a-9659-4def9103e6da.png">
</p>
<ol start="2">
<li>기존에 있었던 나무들은 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에 번식을 진행합니다.
이때 각 칸의 나무 그루 수에서 총 번식이 가능한 칸의 개수만큼 나누어진 그루 수만큼 번식이 되며, 나눌 때 생기는 나머지는 버립니다. 번식의 과정은 모든 나무에서 동시에 일어나게 됩니다.</li>
</ol>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/455b84b9-bb03-442d-aef1-ca9febcf6fe9.png">
</p>
<ol start="3">
<li>각 칸 중 제초제를 뿌렸을 때 나무가 가장 많이 박멸되는 칸에 제초제를 뿌립니다. 제초제를 뿌릴 때 4개의 대각선 방향으로 k칸만큼 전파되게 됩니다. 단 전파되는 도중 벽이 있거나 나무가 아얘 없는 칸이 있는 경우, 그 칸 까지는 제초제가 뿌려지며 그 이후의 칸으로는 제초제가 전파되지 않습니다. 제초제가 뿌려진 칸에는 c년만큼 제초제가 남아있다가 c+1년째가 될 때 사라지게 됩니다. 제초제가 뿌려진 곳에 다시 제초제가 뿌려지는 경우에는 새로 뿌려진 해로부터 다시 c년동안 제초제가 유지됩니다.</li>
</ol>
<p>k가 2일 때, 각각의 칸에 제초제가 뿌려진 경우 박멸되는 나무의 총 그루 수는 다음과 같습니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/2679ea4f-ec6a-475b-ba65-1b48a6c50fbf.png">
</p>
<p>3행 4열이 가장 많은 나무를 박멸시키는 것을 알 수 있으며, 해당 칸에 제초제를 뿌리게 됩니다. 만약 박멸시키는 나무의 수가 동일한 칸이 있는 경우에는 행이 작은 순서대로, 만약 행이 같은 경우에는 열이 작은 칸에 제초제를 뿌리게 됩니다.</p>
<p>3행 4열에 제초제를 뿌린 이후에는 다음과 같이 변하게 됩니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/468fa6cb-e13c-4aa7-84cc-9dc03b5a1ec0.png">
</p>
<p>각 3개의 과정이 1년에 걸쳐 진행된다고 했을 때, m년 동안 총 박멸한 나무의 그루 수를 구하는 프로그램을 작성해보세요.</p>
<p>위의 경우에서 제초제가 1년간 유지된다고 가정했을 때, 그 다음 1년동안의 과정을 그려보면 다음과 같습니다.</p>
<ul>
<li>시작</li>
</ul>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/ea7adf64-b75b-4b30-8d3e-8d20e22447af.png">
</p>
<ul>
<li>나무의 성장</li>
</ul>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/9352da58-b421-44b1-ac6a-a51b4aece925.png">
</p>
<ul>
<li>나무의 번식</li>
</ul>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/84827b55-2db4-43d1-8629-fa89443f276e.png">
</p>
<ul>
<li>제초제를 뿌릴 위치 선정</li>
</ul>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/c15ae6ff-ae33-48a4-a87c-337097e4506a.png">
</p>
<ul>
<li>제초제를 뿌리는 작업 진행</li>
</ul>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2415/images/bde8698c-82cf-47bf-ae4b-dd8dd53adc05.png">
</p>
<h2 id="입력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#입력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>입력 형식</h2>
<p>첫 번째 줄에 격자의 크기 n, 박멸이 진행되는 년 수 m, 제초제의 확산 범위 k, 제초제가 남아있는 년 수 c가 공백을 사이에 두고 주어집니다.</p>
<p>이후 n개의 줄에 걸쳐 각 칸의 나무의 그루 수, 벽의 정보가 주어집니다. 총 나무의 그루 수는 1 이상 100 이하의 수로, 빈 칸은 0, 벽은 -1으로 주어지게 됩니다.</p>
<ul>
<li>5 ≤ n ≤ 20</li>
<li>1 ≤ m ≤ 1000</li>
<li>1 ≤ k ≤ 20</li>
<li>1 ≤ c ≤ 10</li>
</ul>
<h2 id="출력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#출력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>출력 형식</h2>
<p>m년 동안 총 박멸한 나무의 그루 수를 구하세요.</p></div>