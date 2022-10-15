# [보통] 예술성 

[문제 링크](https://www.codetree.ai/frequent-problems/artistry/submissions) 

### 성능 요약

메모리: 18 MB, 시간: 170 ms

### 분류

구현(implementation), 시뮬레이션(simulation)

### 문제 설명

<div class="wmde-markdown wmde-markdown-color "><p>예술가 Sam은 그림에 대한 예술성을 평가하는 알고리즘을 만들어냈습니다. 그림을 편의상 n * n 크기의 격자로 생각하고, 각 칸의 색깔을 1이상 10이하의 숫자로 표현하여 이 알고리즘을 적용해보려 합니다.</p>
<p>다음은 5 * 5 크기의 그림의 예시입니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2413/images/70c1000f-57c9-41df-9627-43ca58533984.png">
</p>
<p>먼저 이 그림에서 동일한 숫자가 상하좌우로 인접해있는 경우 동일한 그룹이라 본다면, 총 4개의 그룹이 만들어지게 됩니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2413/images/af4e33b2-b1d5-44bd-b302-f493b7d28216.png">
</p>
<p>예술 점수는 모든 그룹 쌍의 조화로움의 합으로 정의됩니다. 그룹 a와 그룹 b의 조화로움은 <code>(그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수 ) x 그룹 a를 이루고 있는 숫자 값 x 그룹 b를 이루고 있는 숫자 값 x 그룹 a와 그룹 b가 서로 맞닿아 있는 변의 수</code>로 정의됩니다.</p>
<p>예로 <code>&lt;Figure 2&gt;</code> 에서 두 그룹 G2, G4의 조화로움은 <code>(11 + 8) x 2 x 1 x 4 = 152</code>가 됩니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2413/images/4105ebe8-2aa4-46b8-845c-633e13cd7c76.png">
</p>
<p>그룹 쌍 간의 조화로움 값이 0보다 큰 조합인 (G1, G2), (G2, G3), (G2, G4), (G3, G4) 의 조화로움 값을 전부 더하면 <code>48 + 192 + 152 + 156 = 548</code>이 됩니다. 이를 초기 예술 점수라 부르겠습니다.</p>
<p>초기 예술 점수를 구한 뒤에는 그림에 대한 회전을 진행합니다.</p>
<p>회전은 정중을 기준으로 두 선을 그어 만들어지는 십자 모양과 그 외 부분으로 나뉘어 진행됩니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2413/images/e339e06c-0dbf-41ea-ba9d-9e558c61cb60.png">
</p>
<ul>
<li>십자 모양의 경우 통째로 반시계 방향으로 90' 회전합니다.</li>
</ul>
<p align="center">
<img width="600" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2413/images/6d0d5fd0-dfe4-4c33-8695-bfcb5758c48a.png">
</p>
<ul>
<li>십자 모양을 제외한 4개의 정사각형은 각각 개별적으로 시계 방향으로 90'씩 회전이 진행됩니다.</li>
</ul>
<p align="center">
<img width="600" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2413/images/ffc44531-025b-4e35-b573-068498be5b15.png">
</p>
<p>두 부분에 대한 회전이 동시에 진행되므로 회전 이후 <code>&lt;Figure 4&gt;</code>는 다음 모습이 됩니다.</p>
<p align="center">
<img width="300" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2413/images/f92a9f13-2db2-4689-a23e-c6a7580ef568.png">
</p>
<p>이제 <code>&lt;Figure 7&gt;</code>의 예술 점수 마찬가지 방법으로 구하면 442점이 됩니다. 이는 1회전 이후 예술 점수가 됩니다.</p>
<p>n * n 크기의 그림 정보가 주어졌을 때, 초기 예술 점수, 1회전 이후 예술 점수, 2회전 이후 예술 점수, 그리고 3회전 이후 예술 점수의 합을 구하는 프로그램을 작성해보세요.</p>
<h2 id="입력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#입력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>입력 형식</h2>
<p>첫 번째 줄에 n이 주어집니다. n은 반드시 홀수입니다.<br>
이후 n개의 줄에 걸쳐 각 행에 칠해져 있는 색깔에 대한 정보인 숫자들이 공백을 사이에 두고 주어집니다.</p>
<ul>
<li>3 ≤ n ≤ 29</li>
<li>1 ≤ 주어지는 숫자 ≤ 10</li>
</ul>
<h2 id="출력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#출력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>출력 형식</h2>
<p>첫 번째 줄에 초기 예술 점수, 1회전 이후 예술 점수, 2회전 이후 예술 점수, 그리고 3회전 이후 예술 점수를 모두 합한 값을 출력합니다.</p></div>