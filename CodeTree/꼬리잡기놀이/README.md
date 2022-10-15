# [어려움] 꼬리잡기놀이

[문제 링크](https://www.codetree.ai/frequent-problems/tail-catch-play/description) 

### 성능 요약

메모리: 10MB, 시간: 109 ms

### 분류

구현(implementation), 시뮬레이션(simulation)

### 문제 설명
<div class="wmde-markdown wmde-markdown-color "><p>n * n 격자에서 꼬리잡기놀이를 진행합니다.
꼬리잡기놀이는 다음과 같이 진행됩니다.</p>
<p>3명 이상이 한 팀이 됩니다. 모든 사람들은 자신의 앞 사람의 허리를 잡고 움직이게 되며, 맨 앞에 있는 사람을 <strong>머리사람</strong>, 맨 뒤에 있는 사람을 <strong>꼬리사람</strong>이라고 합니다. 각 팀은 게임에서 주어진 이동 선을 따라서만 이동합니다. 각 팀의 이동 선은 끝이 이어져있습니다. 각 팀의 이동 선은 서로 겹치지 않습니다.</p>
<p>다음과 같이 초기 조건이 주어질 수 있습니다.</p>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/6ea4735c-be16-4b0a-a77c-5c5b05d13917.png">
</p>
<p>게임은 라운드 별로 진행이 되며, 한 라운드는 다음과 같이 진행됩니다.</p>
<ol>
<li>먼저 각 팀은 <strong>머리사람</strong>을 따라서 한 칸 이동합니다.</li>
</ol>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/79577ea4-3de1-4956-b87a-324de4863cba.png">
</p>
<ol start="2">
<li>각 라운드마다 공이 정해진 선을 따라 던져집니다. n개의 행, n개의 열이 주어진다고 했을 때 공이 던져지는 선은 다음과 같습니다.</li>
</ol>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/ba8bdf88-33a8-4877-b4bc-8554613f8210.png">
</p>
<br>
<br>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/d31863f5-cf56-4985-9fb5-623caa27983c.png">
</p>
<br>
<br>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/7c97859e-129f-45d1-89cd-99d414f1a546.png">
</p>
<br>
<br>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/79c0696b-b366-4ad7-8ab5-4a86b76534c7.png">
</p>
<p>4n번째 라운드를 넘어가는 경우에는 다시 1번째 라운드의 방향으로 돌아갑니다.</p>
<ol start="3">
<li>공이 던져지는 경우에 해당 선에 사람이 있으면 최초에 만나게 되는 사람만이 공을 얻게 되어 점수를 얻게 됩니다. 점수는 해당 사람이 <strong>머리사람</strong>을 시작으로 팀 내에서 k번째 사람이라면 k의 제곱만큼 점수를 얻게 됩니다. 아무도 공을 받지 못하는 경우에는 아무 점수도 획득하지 못합니다. 위의 예시에서 1라운드는 다음과 같이 진행됩니다.</li>
</ol>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/efd196f4-bff2-408f-a709-6108dd76a26c.png">
</p>
<p><strong>머리사람</strong>에서 3번째에 있는 사람이 공을 얻었기 때문에 9(3 * 3)점을 획득하게 됩니다. 공을 획득한 팀의 경우에는 <strong>머리사람</strong>과 <strong>꼬리사람</strong>이 바뀝니다. 즉 방향을 바꾸게 됩니다.</p>
<ol start="4">
<li>다음 라운드는 다음과 같이 진행됩니다.</li>
</ol>
<p>4-1. 1라운드가 끝난 후</p>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/1a16095c-e45c-408b-8554-2987f338c2fc.png">
</p>
<p>4-2. 모든 팀 1칸 이동</p>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/a9178e4c-3076-4ec2-9fda-00c784ae1466.png">
</p>
<p>4-3. 공 발사(+ 16점)</p>
<p align="center">
<img width="400" src="https://s3-ap-northeast-2.amazonaws.com/codetreepublic/problems/2414/images/59024735-8fed-4fe2-9780-17d6c44dcf60.png">
</p>
<p>총 격자의 크기, 각 팀의 위치, 각 팀의 이동 선, 총 진행하는 라운드의 수가 주어질 때 각 팀이 획득한 점수의 총합을 구하는 프로그램을 구하세요.</p>
<h2 id="입력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#입력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>입력 형식</h2>
<p>첫 번째 줄에 격자의 크기 n, 팀의 개수 m, 라운드 수 k가 공백을 사이에 두고 주어집니다.</p>
<p>이후 n개의 줄에 걸쳐 각 행에 해당하는 초기 상태의 정보가 공백을 사이에 두고 주어집니다.
0은 빈칸, 1은 머리사람, 2는 머리사람과 꼬리사람이 아닌 나머지, 3은 꼬리사람, 4는 이동 선을 의미합니다.</p>
<p>이동 선의 각 칸은 반드시 2개의 인접한 칸만이 존재하고, 하나의 이동 선에는 하나의 팀만이 존재한다고 가정해도 좋습니다.</p>
<ul>
<li>3 ≤ n ≤ 20</li>
<li>1 ≤ m ≤ 5</li>
<li>1 ≤ k ≤ 1000</li>
</ul>
<h2 id="출력-형식"><a class="anchor" aria-hidden="true" tabindex="-1" href="#출력-형식"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>출력 형식</h2>
<p>k번의 라운드 동안 각 팀이 얻게되는 점수의 총합을 출력합니다.</p></div>