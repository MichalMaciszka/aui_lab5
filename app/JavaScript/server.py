import http.server
import socketserver
PORT = 8000

handler = http.server.SimpleHTTPRequestHandler
handler.extensions_map.update({
    '.js': 'application/javascript',
    ".module.js": "module"
});

httpd = socketserver.TCPServer(("", PORT), handler)

print(handler.extensions_map[".module.js"])
httpd.serve_forever()
print("Serving...")