# [보통] 코드트리 빵

[문제 링크](https://www.codetree.ai/frequent-problems/codetree-mon-bread/description) 

### 성능 요약

메모리: 9 MB, 시간: 89 ms

### 분류

구현(implementation), 시뮬레이션(simulation)

### 문제 설명
<div class="wmde-markdown wmde-markdown-color "><p>최근 코드트리 빵이 전국적으로 인기를 얻어 편의점에서 해당 빵을 구하기 힘들어졌습니다. 빵을 구하고자 하는 m명의 사람이 있는데, 1번 사람은 정확히 1분에, 2번 사람은 정확히 2분에, ..., m번 사람은 정확히 m 분에 각자의 베이스캠프에서 출발하여 편의점으로 이동하기 시작합니다. 사람들은 출발 시간이 되기 전까지 격자 밖에 나와있으며, 사람들이 목표로 하는 편의점은 모두 다릅니다. 이 모든 일은 n*n 크기의 격자 위에서 진행됩니다.</p>
<p>코드트리 빵을 구하고 싶은 사람들은 다음과 같은 방법으로 움직입니다. 이 3가지 행동은 총 1분 동안 진행되며, 정확히 1, 2, 3 순서로 진행되어야 함에 유의합니다.</p>
<ol>
<li>
<p>격자에 있는 사람들이 본인이 가고 싶은 편의점 방향을 향해서 1 칸 움직입니다. 최단거리로 움직이며 최단 거리로 움직이는 방법이 여러가지라면 ↑, ←, →, ↓ 의 우선 순위로 움직이게 됩니다. 여기서 최단거리라 함은 상하좌우 인접한 칸 중 이동가능한 칸으로만 이동하여 도달하기까지 거쳐야 하는 칸의 수가 최소가 되는 거리를 뜻합니다.</p>
</li>
<li>
<p>만약 편의점에 도착한다면 해당 편의점에서 멈추게 되고, 이때부터 다른 사람들은 해당 편의점이 있는 칸을 지나갈 수 없게 됩니다.</p>
</li>
<li>
<p>현재 시간이 t분이고 t ≤ m를 만족한다면, t번 사람은 자신이 가고 싶은 편의점과 가장 가까이 있는 베이스 캠프에 들어갑니다. 여기서 가장 가까이에 있다는 뜻 역시 1에서와 같이 최단거리에 해당하는 곳을 의미합니다. 가장 가까운 베이스캠프가 여러 가지인 경우에는 그 중 행이 작은 베이스캠프, 행이 같다면 열이 작은 베이스 캠프로 들어갑니다. t번 사람이 베이스 캠프로 이동하는 데에는 시간이 전혀 소요되지 않습니다.</p>
<p>이때부터 다른 사람들은 해당 베이스 캠프가 있는 칸을 지나갈 수 없게 됩니다. t번 사람이 편의점을 향해 움직이기 시작했더라도 해당 베이스 캠프는 앞으로 절대 지나갈 수 없음에 유의합니다.</p>
</li>
</ol>
<p>아래와 같이 n = 5, m = 3, 베이스 캠프 4곳, 편의점 3곳이 주어진 경우에 대해서 생각해보겠습니다.</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/6e4ad191-c136-4855-a93a-201fc0bbdf63.png">
</p>
<p>1분에는 격자 내에 아직 사람이 없기 때문에 1, 2번 행동은 진행되지 않으며 3번 행동부터 진행됩니다. 1번 사람이 가고 싶은 편의점과 가장 가까운 베이스캠프는 (2, 1)의 베이스캠프, (2, 5)의 베이스캠프 두 가지 입니다. 이 둘은 행이 같지만 (2, 1)의 베이스캠프가 열이 더 작기 때문에 해당 베이스캠프로 1번 사람은 이동하고, 앞으로 해당 칸으로는 사람이 절대 지나갈 수 없게 됩니다.</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/f1f3e3f2-1c9a-481e-b624-079df8dcf898.png">
</p>
<p>2분에는 1번 사람이 편의점을 향해 한 칸 움직이게 됩니다. 또, 2번 사람이 베이스 캠프에 도착합니다. 이 경우에도 최단 거리에 있는 베이스 캠프가 (4, 2), (5, 5) 두 가지인데, (4, 2)에 있는 베이스캠프가 행이 작기 때문에 (4, 2) 베이스캠프로 이동하게 됩니다.</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/2b49ee95-2be8-4869-83b4-5e69cbff7577.png">
</p>
<p>3분에는 1번 사람이 편의점에 도착하게 되고, 앞으로 해당 칸으로는 사람이 절대 지나갈 수 없게 됩니다. 2번은 한 칸 이동하게 되고, 3번은 새로 베이스캠프에 도착하게 됩니다.</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/e3c91006-76c4-42b4-8401-7d152e26acbc.png">
</p>
<p>이러한 과정을 반복하면 모든 사람이 편의점에 도착할 때까지 총 7분이 걸리게 됩니다. 그 과정은 아래와 같습니다.</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/e725e8ca-5f48-416f-89db-05c9f86f3213.png">
</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/e31ee2d8-b896-448c-8670-a893f6307679.png">
</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/ee1693a6-9ff3-408a-984d-920ece3867e6.png">
</p>
<p align="center">
  <img width="500" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2707/images/ac9e022b-8c3a-47df-8e0c-ea4b6617c6dd.png">
</p>
<p>이미 사람들이 도착한 편의점이나 출발한 적이 있는 베이스캠프의 경우 움직일 때 절대 지나갈 수 없는 공간임을 유의합니다. (예시에서는 빨간색으로 표시되어 있습니다.)</p>
<p>사람들이 위와 같은 방식으로 움직일 때 총 몇 분 후에 모두 편의점에 도착하는지를 구하는 프로그램을 작성해보세요.</p>
<h2 id="입력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#입력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>입력 형식</h2>
<p>첫 번째 줄에는 격자의 크기 n과 사람의 수 m이 공백을 사이에 두고 주어집니다.</p>
<p>이후 n개의 줄에 걸쳐 격자의 정보가 주어집니다. 각 줄에 각각의 행에 해당하는 n개의 수가 공백을 사이에 두고 주어집니다.<br>
0의 경우에는 빈 공간, 1의 경우에는 베이스캠프를 의미합니다.</p>
<p>이후 m개의 줄에 걸쳐 각 사람들이 가고자 하는 편의점 위치의 행 x, 열 y의 정보가 공백을 사이에 두고 주어집니다.</p>
<p>각 사람마다 가고 싶은 편의점의 위치는 겹치지 않으며, 편의점의 위치와 베이스캠프의 위치도 겹치지 않습니다.</p>
<ul>
<li>2 ≤ n ≤ 15</li>
<li>1 ≤ m ≤ <span class="math math-inline"><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>m</mi><mi>i</mi><mi>n</mi><mo stretchy="false">(</mo><msup><mi>n</mi><mn>2</mn></msup><mo separator="true">,</mo><mn>30</mn><mo stretchy="false">)</mo></mrow><annotation encoding="application/x-tex">min(n^2, 30)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1.0641em; vertical-align: -0.25em;"></span><span class="mord mathnormal">min</span><span class="mopen">(</span><span class="mord"><span class="mord mathnormal">n</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height: 0.8141em;"><span style="top: -3.063em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mtight">2</span></span></span></span></span></span></span></span><span class="mpunct">,</span><span class="mspace" style="margin-right: 0.1667em;"></span><span class="mord">30</span><span class="mclose">)</span></span></span></span></span></li>
<li>m ≤ 베이스 캠프의 개수 ≤ <span class="math math-inline"><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><msup><mi>n</mi><mn>2</mn></msup><mo>−</mo><mi>m</mi></mrow><annotation encoding="application/x-tex">n^2 - m</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.8974em; vertical-align: -0.0833em;"></span><span class="mord"><span class="mord mathnormal">n</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height: 0.8141em;"><span style="top: -3.063em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mtight">2</span></span></span></span></span></span></span></span><span class="mspace" style="margin-right: 0.2222em;"></span><span class="mbin">−</span><span class="mspace" style="margin-right: 0.2222em;"></span></span><span class="base"><span class="strut" style="height: 0.4306em;"></span><span class="mord mathnormal">m</span></span></span></span></span></li>
</ul>
<h2 id="출력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#출력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>출력 형식</h2>
<p>모든 사람이 편의점에 도착하는 시간을 출력하세요.</p>
<p>문제 조건에 의해 어떠한 사람이 원하는 편의점에 도달하지 못하게 되는 경우는 절대 발생하지 않음을 가정해도 좋습니다.<br>
또한, 이동하는 도중 동일한 칸에 둘 이상의 사람이 위치하게 되는 경우 역시 가능함에 유의합니다.</p></div>
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