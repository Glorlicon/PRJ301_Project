window.addEventListener('DOMContentLoaded', event => {

    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

function pagger(id,pageindex,totalpage,gap)
{
    var container = document.getElementById(id);
    var result = '';
    if(pageindex - gap > 1)
        result+= '<a href="list?page=1">' + 'First' + '</a>';
    
    for(var i=pageindex-gap;i<pageindex;i++)
        if(i>0)
            result+= '<a href="list?page='+i+'">' + i + '</a>';
    
    result+= '<span>' + pageindex + '</span>';
    
    for(var i=pageindex+1;i<=pageindex + gap;i++)
        if(i<=totalpage)
            result+= '<a href="list?page='+i+'">' + i + '</a>';
    
    if(pageindex + gap < totalpage)
        result+= '<a href="list?page='+totalpage+'">' + 'Last' + '</a>';
    
    container.innerHTML = result;
}