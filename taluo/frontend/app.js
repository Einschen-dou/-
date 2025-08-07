// 塔罗牌数据（模拟）
const tarotCards = [
    { name: "愚人", meaning: "新的开始，冒险，天真，自由" },
    { name: "魔术师", meaning: "创造力，行动，潜能，决心" },
    { name: "女祭司", meaning: "直觉，智慧，神秘，内省" },
    { name: "女皇", meaning: "丰饶，母性，滋养，创造力" },
    { name: "皇帝", meaning: "权威，控制，结构，领导力" },
    { name: "教皇", meaning: "传统，灵性指导，信仰，道德" },
    { name: "恋人", meaning: "选择，关系，和谐，爱" },
    { name: "战车", meaning: "胜利，控制，意志，方向" },
    { name: "力量", meaning: "勇气，力量，耐心，内在力量" },
    { name: "隐士", meaning: "独处，内省，智慧，引导" },
    { name: "命运之轮", meaning: "变化，命运，循环，机遇" },
    { name: "正义", meaning: "平衡，公正，真相，责任" },
    { name: "倒吊人", meaning: "牺牲，新视角，耐心，等待" },
    { name: "死神", meaning: "结束，转变，重生，变革" },
    { name: "节制", meaning: "平衡，调和，耐心，中道" },
    { name: "恶魔", meaning: "诱惑，束缚，欲望，唯物主义" },
    { name: "塔", meaning: "突然变化，崩溃，解放，真相" },
    { name: "星星", meaning: "希望，灵感，灵性，未来" },
    { name: "月亮", meaning: "潜意识，直觉，不确定性，情绪" },
    { name: "太阳", meaning: "喜悦，成功，活力，真相" },
    { name: "审判", meaning: "觉醒，重生，评估，决定" },
    { name: "世界", meaning: "完成，成功，旅行，实现" }
];

// 页面元素
const screens = {
    welcome: document.getElementById('welcome-screen'),
    shuffling: document.getElementById('shuffling-screen'),
    spreadSelection: document.getElementById('spread-selection'),
    result: document.getElementById('result-screen')
};

const elements = {
    startBtn: document.getElementById('start-btn'),
    restartBtn: document.getElementById('restart-btn'),
    questionInput: document.getElementById('question'),
    questionDisplay: document.getElementById('question-display'),
    spreadResult: document.getElementById('spread-result'),
    spreadOptions: document.querySelectorAll('.spread-option')
};

// 当前问题
let currentQuestion = '';

// 初始化
function init() {
    // 开始按钮事件
    elements.startBtn.addEventListener('click', startReading);

    // 重新开始按钮事件
    elements.restartBtn.addEventListener('click', restartReading);

    // 牌阵选择事件
    elements.spreadOptions.forEach(option => {
        option.addEventListener('click', () => {
            const spreadType = option.dataset.spread;
            selectSpread(spreadType);
        });
    });
}

// 开始占卜流程
function startReading() {
    currentQuestion = elements.questionInput.value.trim() || '您当前关注的问题';

    // 显示洗牌界面
    showScreen('shuffling');

    // 模拟洗牌时间（3秒）
    setTimeout(() => {
        showScreen('spreadSelection');
    }, 3000);
}

// 选择牌阵
function selectSpread(spreadType) {
    // 显示结果界面
    showScreen('result');

    // 显示问题
    elements.questionDisplay.textContent = `"${currentQuestion}"`;

    // 根据牌阵类型生成结果
    let resultHtml = '';

    switch(spreadType) {
        case 'single':
            resultHtml = generateSingleCardSpread();
            break;
        case 'three':
            resultHtml = generateThreeCardSpread();
            break;
        case 'six':
            resultHtml = generateSixCardSpread();
            break;
    }

    elements.spreadResult.innerHTML = resultHtml;
}

// 生成单牌阵结果
function generateSingleCardSpread() {
    const card = drawRandomCard();
    return `
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${card.name}</div>
            <div class="card-info">
                <h4>核心牌：${card.name}</h4>
                <p>${card.meaning}</p>
            </div>
        </div>
        <p style="text-align: center; margin-top: 20px;">这张牌代表您当前问题的核心能量和指引。</p>
    `;
}

// 生成三牌阵结果
function generateThreeCardSpread() {
    const cards = drawMultipleCards(3);
    return `
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[0].name}</div>
            <div class="card-info">
                <h4>第一张牌：过去的影响</h4>
                <p>牌名：${cards[0].name}</p>
                <p>${cards[0].meaning}</p>
            </div>
        </div>
        
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[1].name}</div>
            <div class="card-info">
                <h4>第二张牌：现在的状况</h4>
                <p>牌名：${cards[1].name}</p>
                <p>${cards[1].meaning}</p>
            </div>
        </div>
        
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[2].name}</div>
            <div class="card-info">
                <h4>第三张牌：未来的可能</h4>
                <p>牌名：${cards[2].name}</p>
                <p>${cards[2].meaning}</p>
            </div>
        </div>
    `;
}

// 生成六芒星阵结果
function generateSixCardSpread() {
    const cards = drawMultipleCards(6);
    return `
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[0].name}</div>
            <div class="card-info">
                <h4>第一张牌：当前状况</h4>
                <p>牌名：${cards[0].name}</p>
                <p>${cards[0].meaning}</p>
            </div>
        </div>
        
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[1].name}</div>
            <div class="card-info">
                <h4>第二张牌：挑战</h4>
                <p>牌名：${cards[1].name}</p>
                <p>${cards[1].meaning}</p>
            </div>
        </div>
        
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[2].name}</div>
            <div class="card-info">
                <h4>第三张牌：潜意识影响</h4>
                <p>牌名：${cards[2].name}</p>
                <p>${cards[2].meaning}</p>
            </div>
        </div>
        
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[3].name}</div>
            <div class="card-info">
                <h4>第四张牌：过去的影响</h4>
                <p>牌名：${cards[3].name}</p>
                <p>${cards[3].meaning}</p>
            </div>
        </div>
        
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[4].name}</div>
            <div class="card-info">
                <h4>第五张牌：未来的可能</h4>
                <p>牌名：${cards[4].name}</p>
                <p>${cards[4].meaning}</p>
            </div>
        </div>
        
        <div class="card-result">
            <div class="card-image" style="background-image: linear-gradient(135deg, #8b5a2b 0%, #d2b48c 50%, #8b5a2b 100%); display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 1.2rem;">${cards[5].name}</div>
            <div class="card-info">
                <h4>第六张牌：最终结果</h4>
                <p>牌名：${cards[5].name}</p>
                <p>${cards[5].meaning}</p>
            </div>
        </div>
    `;
}

// 抽取随机牌
function drawRandomCard() {
    const randomIndex = Math.floor(Math.random() * tarotCards.length);
    return tarotCards[randomIndex];
}

// 抽取多张不重复的牌
function drawMultipleCards(count) {
    const shuffled = [...tarotCards].sort(() => 0.5 - Math.random());
    return shuffled.slice(0, count);
}

// 显示指定屏幕
function showScreen(screenName) {
    // 隐藏所有屏幕
    Object.values(screens).forEach(screen => {
        screen.classList.remove('active');
    });

    // 显示指定屏幕
    screens[screenName].classList.add('active');
}

// 重新开始占卜
function restartReading() {
    elements.questionInput.value = '';
    showScreen('welcome');
}

// 页面加载完成后初始化
window.addEventListener('DOMContentLoaded', init);