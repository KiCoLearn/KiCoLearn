Date.prototype.format = function(f) {
    if (!this.valueOf()) return ' ';
 
    var weekName = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        const h = d.getHours();
        switch ($1) {
        case 'yyyy': return d.getFullYear();
        case 'yy': return (d.getFullYear() % 1000).pad(2);
        case 'MM': return (d.getMonth() + 1).pad(2);
        case 'dd': return d.getDate().pad(2);
        case 'E': return weekName[d.getDay()];
        case 'HH': return d.getHours().pad(2);
        case 'hh':
            return ((h % 12) ? h : 12).pad(2);
        case 'mm': return d.getMinutes().pad(2);
        case 'ss': return d.getSeconds().pad(2);
        case 'a/p': return d.getHours() < 12 ? '오전' : '오후';
        default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.pad = function(len){return '0'.string(len - this.length) + this;};
Number.prototype.pad = function(len){return this.toString().pad(len);};