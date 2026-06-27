# CSS → SCSS File Mapping for `vetsporvzla`

Paste each block into the indicated file under `src/styles/`.
The `styles.scss` file imports all partials and sets global resets.

---

## `src/styles/styles.scss`
```scss
@use 'variables';
@use 'typography';
@use 'layout';
@use 'animations';
@use 'buttons';
@use 'badges';
@use 'forms';
@use 'modal';
@use 'table';
@use 'cards';
@use 'pagination';

@import url('https://fonts.googleapis.com/css2?family=Sora:wght@500;600;700;800&family=Source+Sans+3:ital,wght@0,400;0,500;0,600;0,700;1,400&display=swap');

* { box-sizing: border-box; margin: 0; padding: 0; }
html { scroll-behavior: smooth; height: 100%; }
body {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  font-family: 'Source Sans 3', system-ui, sans-serif;
  background: var(--bg);
  color: var(--ink);
  line-height: 1.5;
  -webkit-font-smoothing: antialiased;
}
h1,h2,h3,h4 { font-family: 'Sora', sans-serif; letter-spacing: -0.01em; }
button { font-family: inherit; cursor: pointer; border: none; background: none; }
input, select, textarea { font-family: inherit; }
a { color: inherit; }
main { flex: 1; }

::-webkit-scrollbar { height: 9px; width: 9px; }
::-webkit-scrollbar-track { background: transparent; }
::-webkit-scrollbar-thumb { background: #d9d2c7; border-radius: 8px; }
```

---

## `src/styles/_variables.scss`
```scss
// ── CSS custom properties (design tokens) ──
:root {
  --teal:     #0F766E;
  --teal-dk:  #0c5f59;
  --coral:    #F26B4E;
  --coral-dk: #e0573b;
  --lost:     #DC4A4A;
  --found:    #2F9E63;
  --adopt:    #7E5BD0;
  --bg:       #FAF7F2;
  --ink:      #1C2B2A;
  --head:     #163b37;
  --muted:    #6b756f;
  --soft:     #8a938d;
  --border:   #EDE7DC;
  --line:     #F0EAE0;
  --field:    #FCFAF6;
  --field-br: #E6DFD3;
}

// ── SCSS vars (for use in component files) ──
$teal:    var(--teal);
$coral:   var(--coral);
$bg:      var(--bg);
$head:    var(--head);
$border:  var(--border);

// Breakpoints
$bp-tablet: 860px;
$bp-mobile: 600px;
```

---

## `src/styles/_animations.scss`
```scss
@keyframes up {
  from { opacity: 0; transform: translateY(14px); }
  to   { opacity: 1; transform: none; }
}
@keyframes overlay {
  from { opacity: 0; }
  to   { opacity: 1; }
}
@keyframes sheet {
  from { opacity: 0; transform: translateY(22px) scale(0.985); }
  to   { opacity: 1; transform: none; }
}
@keyframes paw {
  0%, 100% { transform: translateY(0) rotate(-6deg); }
  50%       { transform: translateY(-7px) rotate(-6deg); }
}
@keyframes pop {
  from { transform: scale(0.6); opacity: 0; }
  to   { transform: scale(1); opacity: 1; }
}
@keyframes pulseDot {
  0%   { transform: scale(1); opacity: 1; }
  50%  { transform: scale(1.45); opacity: 0.55; }
  100% { transform: scale(1); opacity: 1; }
}
```

---

## `src/styles/_typography.scss`
```scss
// Shared typographic utilities used across views
.eyebrow {
  font-size: 13px;
  font-weight: 700;
  letter-spacing: 0.1em;
  text-transform: uppercase;
}
.dot {
  display: inline-block;
  width: 11px;
  height: 11px;
  border-radius: 50%;
}
.mono {
  font-family: ui-monospace, Menlo, monospace;
  font-size: 11px;
  letter-spacing: 0.05em;
  color: rgba(28, 43, 42, 0.5);
}
```

---

## `src/styles/_layout.scss`
```scss
.wrap { max-width: 1240px; margin: 0 auto; padding: 0 22px; }
.page-pad { padding: 40px 0 90px; }
.page-h1 { font-weight: 800; font-size: 34px; color: var(--head); letter-spacing: -0.02em; }
.lead { color: var(--muted); font-size: 16px; margin-top: 6px; max-width: 620px; text-wrap: pretty; }

@media (max-width: 860px) {
  .alert-in { flex-direction: column; align-items: flex-start; }
  .alert-btn { width: 100%; text-align: center; }
}

@media (max-width: 600px) {
  .wrap { padding: 0 16px; }
  .page-pad { padding: 28px 0 60px; }
  .page-h1 { font-size: 26px; }
  .lead { font-size: 14.5px; }
}

@media (max-width: 400px) {
  .wrap { padding: 0 12px; }
}
```

---

## `src/styles/_buttons.scss`
```scss
.btn-teal {
  background: var(--teal); color: #fff; font-weight: 600; font-size: 14.5px;
  padding: 12px 18px; border-radius: 11px; display: flex; align-items: center; gap: 8px;
  box-shadow: 0 6px 18px rgba(15,118,110,.28); transition: 0.15s;
  &:hover { transform: translateY(-2px); }
}

.btn-vet {
  border: 1.5px solid #cfddd9; color: var(--teal); font-weight: 600; font-size: 13.5px;
  padding: 8px 13px; border-radius: 9px; display: flex; align-items: center; gap: 6px; transition: 0.15s;
  &:hover { background: #ecf4f2; border-color: var(--teal); }
}

.btn-report {
  background: var(--coral); color: #fff; font-weight: 600; font-size: 13.5px;
  padding: 9px 15px; border-radius: 9px; box-shadow: 0 3px 12px rgba(242,107,78,.32); transition: 0.15s;
  &:hover { transform: translateY(-1px); }
}

.cta-coral {
  background: var(--coral); color: #fff; font-weight: 700; font-size: 16px;
  padding: 15px 24px; border-radius: 12px; transition: 0.15s;
  &:hover { transform: translateY(-2px); }
}

.cta-ghost {
  border: 1.5px solid rgba(255,255,255,.4); background: rgba(255,255,255,.08); color: #fff;
  font-weight: 700; font-size: 16px; padding: 15px 24px; border-radius: 12px; transition: 0.15s;
  &:hover { background: rgba(255,255,255,.18); }
}

.fab {
  position: fixed; bottom: 26px; right: 26px; z-index: 45;
  background: var(--coral); color: #fff; font-weight: 700; font-size: 15px;
  padding: 15px 22px; border-radius: 999px; box-shadow: 0 10px 30px rgba(242,107,78,.45);
  display: none; align-items: center; gap: 9px; transition: 0.15s;
  &.show { display: flex; }
  &:hover { transform: translateY(-3px) scale(1.03); }
}

.bt-approve {
  background: var(--found); color: #fff; font-weight: 600; font-size: 12.5px;
  padding: 7px 11px; border-radius: 8px;
  &:hover { background: #27824f; }
}

.bt-reject {
  border: 1.5px solid #f0c9c9; background: #fff; color: var(--lost);
  font-weight: 600; font-size: 12.5px; padding: 7px 11px; border-radius: 8px;
  &:hover { background: #fdf2f2; }
}

.bt-info {
  border: 1.5px solid var(--field-br); background: #fff; color: var(--muted);
  font-weight: 600; font-size: 12.5px; padding: 7px 10px; border-radius: 8px;
  &:hover { background: var(--field); }
}

.btn-vm-save {
  background: var(--teal); color: #fff; font-weight: 700; font-size: 14px;
  padding: 10px 20px; border-radius: 10px; box-shadow: 0 4px 12px rgba(15,118,110,.25); transition: 0.15s;
  &:hover { background: var(--teal-dk); }
}

.btn-vm-cancel {
  border: 1.5px solid var(--field-br); background: #fff; color: #566159;
  font-weight: 600; font-size: 14px; padding: 10px 16px; border-radius: 10px;
  &:hover { background: var(--field); }
}
```

---

## `src/styles/_badges.scss`
```scss
.stpill {
  font-size: 12px; font-weight: 700; padding: 5px 11px;
  border-radius: 999px; white-space: nowrap; display: inline-block;
}
.pill-pending {
  background: #fdeee9; color: var(--coral); font-size: 12px;
  font-weight: 700; padding: 4px 11px; border-radius: 999px;
}
.badge {
  position: absolute; top: 11px; left: 11px; color: #fff;
  font-size: 11.5px; font-weight: 700; letter-spacing: 0.05em;
  padding: 5px 11px; border-radius: 999px; display: flex; align-items: center; gap: 5px;
  &.lost  { background: var(--lost);  box-shadow: 0 2px 8px rgba(220,74,74,.4); }
  &.found { background: var(--found); box-shadow: 0 2px 8px rgba(47,158,99,.4); }
}
.spill {
  font-size: 11.5px; font-weight: 700; padding: 5px 10px; border-radius: 999px; white-space: nowrap;
  &.open { background: #e7f5ef; color: #1b7a55; }
  &.full { background: #fdecec; color: var(--lost); }
}
.sv {
  background: #F0F7F5; color: var(--teal); font-size: 12.5px; font-weight: 600;
  padding: 5px 11px; border-radius: 8px; display: flex; align-items: center; gap: 5px;
}
.vm-st-opt {
  padding: 8px 14px; border-radius: 10px; font-size: 13px; font-weight: 600;
  cursor: pointer; border: 2px solid transparent; opacity: 0.6;
  transition: opacity 0.15s, border-color 0.15s;
  &:hover { opacity: 0.85; }
  &.sel   { opacity: 1; border-color: currentColor; }
}
.vm-flash {
  font-size: 13px; font-weight: 700; color: #1b7a55; display: none;
  align-items: center; gap: 5px; margin-right: auto;
  &.show { display: flex; animation: up 0.2s ease both; }
}
```

---

## `src/styles/_forms.scss`
```scss
.inp {
  width: 100%; margin-top: 5px; border: 1.5px solid var(--field-br);
  background: var(--field); border-radius: 10px; padding: 11px 13px;
  font-size: 14.5px; outline: none;
  &:focus { border-color: var(--teal); }
}
textarea.inp { min-height: 90px; resize: vertical; }
.fcol { display: flex; flex-direction: column; gap: 13px; margin-top: 20px; }
.frow { display: flex; gap: 11px; flex-wrap: wrap; }
.frow > div { flex: 1; min-width: 120px; }
label { font-size: 13px; font-weight: 600; color: #33403c; }
.note { border: 1px dashed #cfe0da; background: #f0f7f5; border-radius: 12px; padding: 12px 14px; display: flex; gap: 10px; align-items: center; font-size: 13px; color: #3d7d63; }
.check { display: flex; align-items: center; gap: 9px; font-size: 13.5px; color: #566159; cursor: pointer; margin-top: 2px; input { width: 17px; height: 17px; accent-color: var(--teal); } }

.vf-select {
  font-size: 13.5px; padding: 9px 12px; border-radius: 10px;
  border: 1.5px solid var(--field-br); background: #fff; color: var(--ink);
  font-family: inherit; cursor: pointer;
  &:focus { outline: none; border-color: var(--teal); }
}

.lf-group { display: flex; flex-direction: column; gap: 6px; }
.lf-group label { font-size: 13px; font-weight: 700; color: var(--head); letter-spacing: 0.01em; }
.lf-inp {
  padding: 12px 14px; border: 1.5px solid var(--field-br); border-radius: 11px;
  font-size: 15px; background: #fff; color: var(--ink); transition: border-color 0.2s, box-shadow 0.2s; outline: none;
  &:focus { border-color: var(--teal); box-shadow: 0 0 0 3px rgba(15,118,110,.1); }
}
.lf-pw-wrap { position: relative; .lf-inp { width: 100%; padding-right: 44px; } }
.lf-eye { position: absolute; right: 12px; top: 50%; transform: translateY(-50%); font-size: 17px; opacity: 0.5; transition: opacity 0.15s; background: none; border: none; cursor: pointer; padding: 2px; &:hover { opacity: 0.8; } }
.lf-submit { background: var(--teal); color: #fff; font-weight: 700; font-size: 16px; padding: 14px; border-radius: 12px; box-shadow: 0 6px 18px rgba(15,118,110,.3); transition: 0.15s; margin-top: 4px; &:hover { background: var(--teal-dk); transform: translateY(-1px); } &:active { transform: none; } }

.vm-obs-wrap { border: 1.5px solid var(--field-br); border-radius: 12px; overflow: hidden; transition: border-color 0.2s; background: #fff; &:focus-within { border-color: var(--teal); } }
.vm-obs-lbl { font-size: 10.5px; font-weight: 700; color: var(--muted); letter-spacing: 0.05em; text-transform: uppercase; padding: 10px 14px 3px; display: block; }
.vm-obs-wrap textarea { width: 100%; min-height: 84px; padding: 6px 14px 12px; font-size: 14px; color: var(--ink); resize: vertical; border: none; outline: none; background: transparent; font-family: inherit; line-height: 1.5; }

@media (max-width: 600px) {
  .fcol { gap: 10px; }
  .frow { flex-direction: column; gap: 10px; }
}
```

---

## `src/styles/_modal.scss`
```scss
.overlay {
  position: fixed; inset: 0; z-index: 60; background: rgba(16,49,45,.55);
  backdrop-filter: blur(4px); display: none; align-items: center; justify-content: center; padding: 18px;
  &.show { display: flex; animation: overlay 0.2s ease both; }
}
.modal {
  background: var(--bg); border-radius: 22px; width: 100%; max-width: 620px; max-height: 92vh;
  overflow: hidden; display: flex; flex-direction: column;
  box-shadow: 0 40px 90px rgba(7,40,36,.5); animation: sheet 0.28s cubic-bezier(0.2,0.8,0.2,1) both;
}
.modal-head { padding: 20px 24px 0; flex-shrink: 0; .top { display: flex; align-items: center; justify-content: space-between; } .ti { display: flex; align-items: center; gap: 10px; } .ic { width: 34px; height: 34px; border-radius: 10px; background: var(--teal); display: flex; align-items: center; justify-content: center; font-size: 16px; } h3 { font-weight: 700; font-size: 18px; color: var(--head); } }
.x { width: 34px; height: 34px; border-radius: 9px; background: var(--border); color: var(--muted); font-size: 17px; display: flex; align-items: center; justify-content: center; &:hover { background: #e2dace; } }
.progress-wrap { margin-top: 18px; }
.steplabels { display: flex; justify-content: space-between; margin-bottom: 8px; }
.steplabels b, .slabel { flex: 1; text-align: center; font-size: 11.5px; font-weight: 700; color: #bcb6aa; letter-spacing: 0.02em; transition: color 0.3s; &.done { color: var(--teal); } }
.ptrack { height: 7px; background: #EAE3D8; border-radius: 999px; overflow: hidden; }
.pfill { height: 100%; background: linear-gradient(90deg,#0F766E,#15a59a); border-radius: 999px; width: 20%; transition: width 0.35s cubic-bezier(0.4,0,0.2,1); }
.modal-body { padding: 24px; overflow-y: auto; flex: 1; }
.mstep { display: none; }
.mstep.active { display: block; animation: up 0.25s ease both; }
.mstep h4 { font-weight: 700; font-size: 17px; color: var(--head); margin-bottom: 4px; }
.mstep .sub { color: var(--muted); font-size: 14px; margin-bottom: 18px; }
.modal-foot { padding: 16px 24px; border-top: 1px solid var(--border); flex-shrink: 0; display: flex; align-items: center; justify-content: space-between; gap: 12px; background: #fff; }
.m-back { border: 1.5px solid var(--field-br); background: #fff; color: #566159; font-weight: 600; font-size: 14.5px; padding: 11px 18px; border-radius: 11px; &:hover { background: var(--field); } }
.m-count { font-size: 13px; color: #9aa49f; font-weight: 600; }
.m-next { background: var(--teal); color: #fff; font-weight: 700; font-size: 14.5px; padding: 11px 22px; border-radius: 11px; box-shadow: 0 5px 14px rgba(15,118,110,.3); &:hover { background: var(--teal-dk); } }
.dropzone { border: 2px dashed #bcd6cf; background: #f0f7f5; border-radius: 16px; padding: 38px 20px; text-align: center; cursor: pointer; transition: 0.15s; &:hover, &.drag { background: #e7f2ef; border-color: var(--teal); } .em { font-size: 42px; } b { font-weight: 700; font-size: 16px; color: var(--head); margin-top: 10px; display: block; } small { font-size: 13.5px; color: var(--muted); } }
.thumbs { display: flex; gap: 10px; margin-top: 14px; }
.thumb { flex: 1; height: 74px; border-radius: 11px; background: linear-gradient(140deg,#E3F0EE,#cfe6e2); display: flex; align-items: center; justify-content: center; font-size: 24px; opacity: 0.7; &.empty { background: none; border: 1.5px dashed #d9d2c7; color: #bcb6aa; } }
.success { text-align: center; padding: 18px 10px; .circle { width: 78px; height: 78px; border-radius: 50%; background: #e7f5ef; display: flex; align-items: center; justify-content: center; font-size: 40px; margin: 0 auto; animation: pop 0.4s cubic-bezier(0.2,0.9,0.3,1.3) both; } h3 { font-weight: 800; font-size: 23px; color: var(--head); margin-top: 18px; } p { color: #566159; font-size: 15px; margin-top: 8px; max-width: 400px; margin: 8px auto 0; } .info { background: #fff; border: 1px solid var(--border); border-radius: 13px; padding: 14px 18px; margin: 22px auto 0; max-width: 340px; display: flex; align-items: center; gap: 12px; text-align: left; } }

// Vet detail modals
.vmodal-overlay { position: fixed; inset: 0; z-index: 70; background: rgba(16,49,45,.58); backdrop-filter: blur(5px); display: none; align-items: center; justify-content: center; padding: 18px; &.show { display: flex; animation: overlay 0.2s ease both; } }
.vmodal { background: var(--bg); border-radius: 22px; width: 100%; max-width: 660px; max-height: 93vh; overflow: hidden; display: flex; flex-direction: column; box-shadow: 0 40px 90px rgba(7,40,36,.55); animation: sheet 0.28s cubic-bezier(0.2,0.8,0.2,1) both; }
.vmodal-head { padding: 18px 24px; flex-shrink: 0; border-bottom: 1px solid var(--line); display: flex; align-items: center; justify-content: space-between; gap: 12px; .ti { display: flex; align-items: center; gap: 10px; } .ic { width: 36px; height: 36px; border-radius: 10px; display: flex; align-items: center; justify-content: center; font-size: 17px; flex-shrink: 0; } h3 { font-weight: 700; font-size: 18px; color: var(--head); } small { font-size: 12px; color: var(--muted); } }
.vmodal-body { overflow-y: auto; flex: 1; }
.vmodal-foot { padding: 14px 24px; border-top: 1px solid var(--border); flex-shrink: 0; display: flex; align-items: center; gap: 10px; background: #fff; flex-wrap: wrap; }
.vm-sec { padding: 18px 24px; border-bottom: 1px solid var(--line); &:last-child { border-bottom: none; } }
.vm-sec-title { font-size: 11px; font-weight: 800; letter-spacing: 0.07em; text-transform: uppercase; color: var(--muted); margin-bottom: 12px; }
.vm-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 10px 20px; &.g3 { grid-template-columns: 1fr 1fr 1fr; } }
.vm-field { label { font-size: 11px; font-weight: 700; color: var(--muted); letter-spacing: 0.04em; text-transform: uppercase; display: block; margin-bottom: 2px; } span { font-size: 14px; color: var(--ink); } span.em { font-weight: 700; color: var(--head); font-size: 15px; } }
.vm-photo { height: 120px; border-radius: 14px; display: flex; align-items: center; justify-content: center; font-size: 52px; position: relative; overflow: hidden; }
.vm-photo-lbl { position: absolute; bottom: 8px; right: 10px; font-size: 10px; font-weight: 700; color: rgba(255,255,255,.65); letter-spacing: 0.04em; text-transform: uppercase; }
.vm-st-picker { display: flex; gap: 8px; flex-wrap: wrap; margin-top: 8px; }

@media (max-width: 600px) {
  .modal-head { padding: 14px 16px 10px; }
  .modal-body { padding: 14px 16px; }
  .modal-foot { padding: 10px 16px 16px; }
}
```

---

## `src/styles/_table.scss`
```scss
.table { min-width: 680px; }
.trow { display: grid; grid-template-columns: 1.4fr 1fr 0.9fr 1fr 1.5fr; gap: 12px; padding: 14px 22px; border-top: 1px solid #F4EFE7; align-items: center; .who { display: flex; align-items: center; gap: 10px; } .av { width: 34px; height: 34px; border-radius: 50%; font-weight: 700; font-size: 13px; display: flex; align-items: center; justify-content: center; flex-shrink: 0; } .animal { font-size: 14px; color: #33403c; font-weight: 600; } .date { font-size: 13.5px; color: var(--soft); } .acts { display: flex; gap: 6px; justify-content: flex-end; } }
.thead { padding: 11px 22px; background: #FCFAF6; font-size: 12px; font-weight: 700; color: #9aa49f; letter-spacing: 0.04em; text-transform: uppercase; border-top: none; }
.vtable { width: 100%; }
.vtrow { display: grid; gap: 12px; padding: 13px 22px; border-top: 1px solid #F4EFE7; align-items: center; .who { display: flex; align-items: center; gap: 10px; } .av { width: 34px; height: 34px; border-radius: 50%; font-weight: 700; font-size: 13px; display: flex; align-items: center; justify-content: center; flex-shrink: 0; } }
.vthead { padding: 10px 22px; background: #FCFAF6; font-size: 11.5px; font-weight: 700; color: #9aa49f; letter-spacing: 0.04em; text-transform: uppercase; border-top: none; }
.vtacts { display: flex; gap: 6px; justify-content: flex-end; flex-wrap: wrap; }
.resolved { font-size: 13px; font-weight: 600; display: flex; align-items: center; gap: 5px; }
.table-scroll { overflow-x: auto; }
.panel { background: #fff; border: 1px solid var(--border); border-radius: 18px; margin-top: 20px; overflow: hidden; box-shadow: 0 1px 2px rgba(28,43,42,.04); }
.panel-head { display: flex; align-items: center; justify-content: space-between; padding: 18px 22px; border-bottom: 1px solid var(--line); h3 { font-weight: 700; font-size: 17px; color: var(--head); } }
.cap-bar  { height: 6px; border-radius: 4px; background: #EDE7DC; margin-top: 4px; overflow: hidden; }
.cap-fill { height: 100%; border-radius: 4px; }
```

---

## `src/styles/_cards.scss`
```scss
// Pet cards
.grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(248px,1fr)); gap: 18px; margin-top: 22px; }
.pet { background: #fff; border: 1px solid var(--border); border-radius: 16px; overflow: hidden; box-shadow: 0 1px 2px rgba(28,43,42,.04); transition: 0.18s; &:hover { transform: translateY(-4px); box-shadow: 0 14px 30px rgba(28,43,42,.12); } .photo { position: relative; height: 182px; display: flex; align-items: center; justify-content: center; } .photo .pw { font-size: 54px; opacity: 0.4; } .body { padding: 15px 16px; } .pname { font-weight: 700; font-size: 18px; color: var(--head); } .breed { font-size: 13px; color: var(--soft); } }
.statuschip { position: absolute; bottom: 9px; left: 11px; background: rgba(255,255,255,.92); color: #33403c; font-size: 11px; font-weight: 600; padding: 4px 9px; border-radius: 999px; display: flex; align-items: center; gap: 5px; }
.meta { display: flex; align-items: center; gap: 6px; font-size: 13px; color: var(--muted); margin-top: 10px; &.t { color: #9aa49f; margin-top: 3px; } }
.actions { display: flex; gap: 8px; margin-top: 14px; }
.act { flex: 1; color: #fff; font-weight: 600; font-size: 13px; padding: 9px; border-radius: 9px; display: flex; align-items: center; justify-content: center; gap: 5px; transition: 0.15s; &.call { background: var(--teal); &:hover { background: var(--teal-dk); } } &.wa { background: #25864e; &:hover { background: #1f6f41; } } }
.share { width: 100%; border: 1.5px solid var(--field-br); background: #fff; color: var(--muted); font-weight: 600; font-size: 13px; padding: 8px; border-radius: 9px; margin-top: 8px; display: flex; align-items: center; justify-content: center; gap: 6px; transition: 0.15s; &:hover { background: var(--field); border-color: #d9d2c7; } }

// Shelters
.shelters { display: grid; grid-template-columns: repeat(auto-fill, minmax(330px,1fr)); gap: 18px; margin-top: 22px; }
.shelter { background: #fff; border: 1px solid var(--border); border-radius: 18px; padding: 20px; box-shadow: 0 1px 2px rgba(28,43,42,.04); transition: 0.18s; &:hover { box-shadow: 0 14px 30px rgba(28,43,42,.1); } .top { display: flex; align-items: start; justify-content: space-between; gap: 10px; } h3 { font-weight: 700; font-size: 19px; color: var(--head); } .addr { display: flex; align-items: center; gap: 6px; font-size: 13.5px; color: var(--muted); margin-top: 5px; } }
.bars { margin-top: 18px; display: flex; flex-direction: column; gap: 13px; }
.bar { .brow { display: flex; justify-content: space-between; font-size: 13px; margin-bottom: 5px; } .brow b { font-weight: 600; color: #33403c; } .brow span { color: var(--soft); } }
.track { height: 9px; background: var(--border); border-radius: 999px; overflow: hidden; }
.fill  { height: 100%; border-radius: 999px; transition: width 0.6s ease; }
.svchips { display: flex; gap: 7px; flex-wrap: wrap; margin-top: 18px; }
.sv { background: #F0F7F5; color: var(--teal); font-size: 12.5px; font-weight: 600; padding: 5px 11px; border-radius: 8px; display: flex; align-items: center; gap: 5px; }

// Adoption
.adopt-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(252px,1fr)); gap: 18px; margin-top: 24px; }
.acard { background: #fff; border: 1px solid var(--border); border-radius: 18px; overflow: hidden; box-shadow: 0 1px 2px rgba(28,43,42,.04); transition: 0.18s; &:hover { transform: translateY(-4px); box-shadow: 0 16px 32px rgba(28,43,42,.12); } .photo { position: relative; height: 190px; display: flex; align-items: center; justify-content: center; } .photo .pw { font-size: 56px; opacity: 0.4; } .body { padding: 16px; } .pname { font-weight: 700; font-size: 19px; color: var(--head); } .age { font-size: 12.5px; color: #9aa49f; } }
.sbadge { position: absolute; top: 11px; left: 11px; color: #fff; font-size: 11px; font-weight: 700; letter-spacing: 0.04em; padding: 5px 10px; border-radius: 999px; }
.tags { display: flex; gap: 7px; margin-top: 10px; flex-wrap: wrap; }
.tag { font-size: 12px; font-weight: 600; padding: 4px 9px; border-radius: 7px; &.size { background: #F4F0E8; color: var(--muted); } }
.vetseal { display: flex; align-items: center; gap: 7px; margin-top: 13px; background: #e7f5ef; border: 1px solid #cfe9df; border-radius: 9px; padding: 8px 10px; span { font-size: 12.5px; font-weight: 600; color: #1b7a55; } }
.btn-adopt { width: 100%; background: var(--adopt); color: #fff; font-weight: 600; font-size: 14px; padding: 11px; border-radius: 10px; margin-top: 13px; display: flex; align-items: center; justify-content: center; gap: 7px; box-shadow: 0 5px 14px rgba(126,91,208,.3); transition: 0.15s; &:hover { transform: translateY(-1px); } }

// Feature cards
.feat-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(232px,1fr)); gap: 18px; margin-top: 26px; }
.feat { background: #fff; border: 1px solid var(--border); border-radius: 18px; padding: 24px 22px; cursor: pointer; transition: 0.18s; position: relative; overflow: hidden; &:hover { transform: translateY(-5px); box-shadow: 0 18px 36px rgba(28,43,42,.1); } .paww { position: absolute; top: -14px; right: -8px; font-size: 64px; opacity: 0.06; } .ic { width: 48px; height: 48px; border-radius: 13px; display: flex; align-items: center; justify-content: center; font-size: 23px; margin-bottom: 16px; } h3 { font-weight: 700; font-size: 18px; color: var(--head); } p { color: var(--muted); font-size: 14.5px; margin-top: 7px; } .go { margin-top: 15px; font-weight: 700; font-size: 14px; display: flex; align-items: center; gap: 6px; } }

// Stat cards (vet)
.statgrid { display: grid; grid-template-columns: repeat(auto-fit, minmax(180px,1fr)); gap: 14px; }
.stat { background: #fff; border: 1px solid var(--border); border-radius: 16px; padding: 18px 20px; box-shadow: 0 1px 2px rgba(28,43,42,.04); .r { display: flex; align-items: center; justify-content: space-between; } .ic { width: 38px; height: 38px; border-radius: 11px; display: flex; align-items: center; justify-content: center; font-size: 19px; } .delta { font-size: 12px; font-weight: 700; } .n { font-weight: 800; font-size: 30px; color: var(--head); margin-top: 12px; } .l { color: #8a938d; font-size: 13.5px; font-weight: 500; } }

// Vet animal mini list
.animals { background: #fff; border: 1px solid var(--border); border-radius: 18px; margin-top: 20px; padding: 20px 22px; box-shadow: 0 1px 2px rgba(28,43,42,.04); }
.animals .h { display: flex; align-items: center; justify-content: space-between; margin-bottom: 16px; h3 { font-weight: 700; font-size: 17px; color: var(--head); } }
.aglist { display: grid; grid-template-columns: repeat(auto-fill, minmax(190px,1fr)); gap: 13px; }
.aitem { border: 1px solid var(--line); border-radius: 13px; padding: 12px; display: flex; align-items: center; gap: 11px; .av { width: 46px; height: 46px; border-radius: 11px; display: flex; align-items: center; justify-content: center; font-size: 21px; flex-shrink: 0; } b { font-weight: 700; font-size: 14px; color: #33403c; } small { font-size: 12px; color: #9aa49f; display: block; } .st { display: inline-block; margin-top: 4px; font-size: 10.5px; font-weight: 700; padding: 2px 7px; border-radius: 6px; } }

// Latest strip (home)
.strip { display: flex; gap: 16px; overflow-x: auto; padding: 4px 2px 16px; scroll-snap-type: x mandatory; &::-webkit-scrollbar { height: 6px; } }
.mini { flex: 0 0 230px; scroll-snap-align: start; background: #fff; border: 1px solid var(--border); border-radius: 16px; overflow: hidden; box-shadow: 0 1px 2px rgba(28,43,42,.04); transition: 0.18s; &:hover { transform: translateY(-4px); box-shadow: 0 14px 30px rgba(28,43,42,.12); } .photo { position: relative; height: 140px; display: flex; align-items: center; justify-content: center; } .photo .pw { font-size: 46px; opacity: 0.4; } .body { padding: 13px 14px; } .name { font-weight: 700; font-size: 16px; color: var(--head); } }

@media (max-width: 600px) {
  .grid { grid-template-columns: 1fr; gap: 14px; }
  .pet .photo { height: 160px; }
  .shelter { padding: 18px 16px; }
  .mini { flex: 0 0 200px; }
  .feat-grid { grid-template-columns: 1fr; gap: 14px; }
  .statgrid { grid-template-columns: 1fr 1fr; }
}
@media (max-width: 400px) {
  .statgrid { grid-template-columns: 1fr; }
}
```

---

## `src/styles/_pagination.scss`
```scss
.pgn { display: flex; align-items: center; justify-content: center; gap: 6px; padding: 18px 0 4px; flex-wrap: wrap; }
.pgn-btn { width: 34px; height: 34px; border-radius: 9px; border: 1.5px solid var(--border); background: #fff; color: var(--ink); font-size: 13.5px; font-weight: 600; cursor: pointer; display: flex; align-items: center; justify-content: center; transition: 0.15s; font-family: inherit; &:hover:not(:disabled):not(.pgn-active) { background: var(--field); border-color: #cfddd9; } &.pgn-active { background: var(--teal); color: #fff; border-color: var(--teal); box-shadow: 0 2px 8px rgba(15,118,110,.25); } &:disabled { opacity: 0.35; cursor: not-allowed; } }
.pgn-info { font-size: 12.5px; color: var(--muted); padding: 0 6px; font-weight: 600; }
```

---

## Component-level SCSS files

These are styles **not** in the global partials — they belong to individual components.

| Component | File | What goes there |
|---|---|---|
| `shared/components/header` | `header.component.scss` | `header`, `.hrow`, `.brand`, `.logo`, `.nav-link`, `.menu-toggle`, `.flag`, `.embar`, `.alert-banner`, `.alert-*` |
| `shared/components/footer` | `footer.component.scss` | `footer`, `.foot`, `.foot-bot`, `.flinks`, `.fcontact`, `.socials`, `.wa-emerg` |
| `features/public/home` | `home.component.scss` | `.hero`, `.hero-*`, `.cta-*`, `.hero-stats`, `.section`, `.sec-head`, `.howbg`, `.how`, `.how-grid`, `.step`, `.band`, `.strip-head`, `.feat-outer`, `.cta-band-wrap` |
| `features/public/lost` | `lost.component.scss` | `.filterbar`, `.chips`, `.chip`, `.search`, `.fsel`, `.count` (filter bar shared with found) |
| `features/public/found` | `found.component.scss` | Same as lost |
| `features/public/shelters` | `shelters.component.scss` | `.map`, `.map .gridlines`, `.map .pin`, `.map .lbl` |
| `features/public/adoptions` | `adoptions.component.scss` | `.tabs`, `.tab`, `.apply-grid`, `.form-card`, `.aside-card`, `.proc`, `.guarantee` |
| `features/public/about` | `about.component.scss` | `.about-hero`, `.values`, `.value`, `.statband`, `.join` |
| `features/auth/login` | `login.component.scss` | `.login-wrap`, `.login-card`, `.login-brand`, `.login-logo`, `.login-hd`, `.login-error`, `.login-form`, `.login-foot`, `.login-back`, `.login-deco`, `.login-deco-*` |
| `features/vet/vet-layout` | `vet-layout.component.scss` | `.vetlayout`, `.sidebar`, `.sb-user`, `.sb-menu`, `.sb-item`, `.sb-exit`, `.vv` |
| `features/vet/dashboard` | `dashboard.component.scss` | `.vmain-head` |
| `features/vet/solicitudes` | `solicitudes.component.scss` | (uses global `.vtable`, `.vtrow`, `.pgn`) |
| `features/vet/animales` | `animales.component.scss` | (uses global `.vtable`, `.vtrow`, `.pgn`) |
| `features/vet/refugios` | `refugios-vet.component.scss` | (uses global `.vtable`, `.vtrow`, `.pgn`) |
