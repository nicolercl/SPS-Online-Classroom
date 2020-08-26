async function loadDashboardPage(){
    checkLoginStatus();
    getCourseList();
}

async function loadCoursePage(){
    checkLoginStatus();
    const urlParams = new URLSearchParams(window.location.search);
    const courseTitle = urlParams.get('course');
    getCourse(courseTitle);
}

async function enrollInCourse(){
    const urlParams = new URLSearchParams(window.location.search);
    const response = await fetch('/enroll-course', {
        method: 'post',
        headers:new Headers({
            'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }),
        body: urlParams
    });
}

async function checkLoginStatus() {
    const loginMessageElement = document.getElementById("login-container");
    const response = await fetch('/login');
    const text = await response.text();
    loginMessageElement.innerHTML = text;
}

async function getCourseList() {
    const coursesContainer = document.getElementById("courses-container");
    const allCoursesContainer = document.getElementById("all-courses-container");

    const response = await fetch('/dashboard');
    const [courses, allCourses] = await response.json();

    if (courses.length != 0) {
        courses.forEach((course) => {
            let courseDiv = buildButtonDiv(course);
            coursesContainer.appendChild(courseDiv);
        });
    }
    if (allCourses.length != 0) {
        allCourses.forEach((course) => {
            let courseDiv = buildButtonDiv(course);
            allCoursesContainer.appendChild(courseDiv);
        });
    }
}

function buildButtonDiv(text){
    const buttonDiv = document.createElement('button');
    buttonDiv.addEventListener('click', () => 
        { window.location.href = "/course.html?course=" + text});
    buttonDiv.innerText = text;
    return buttonDiv;
}

async function getCourse(courseTitle) {
    const params = new URLSearchParams();
    params.append('courseTitle', courseTitle);
    const response = await fetch('/fetch-course', {
        method: 'post',
        headers:new Headers({
            'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }),
        body: params
    });
    const course = await response.json();
    document.getElementById("course-title").innerHTML = course.mTitle;
    document.getElementById("professor").innerHTML = course.mProfessor;
    document.getElementById('student-list').innerHTML = course.mStudents;
    document.getElementById('video-list').innerHTML = course.mVideos;
    document.getElementById('materials').innerHTML = course.mMaterials;
}